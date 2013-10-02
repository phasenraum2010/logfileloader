package org.woehlke.logfileloader.eai.pipelines;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.Message;
import org.springframework.integration.annotation.Aggregator;
import org.springframework.integration.annotation.CorrelationStrategy;
import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.MessageEndpoint;
import org.woehlke.logfileloader.eai.events.TriggerStartupEvent;
import org.woehlke.logfileloader.eai.service.ManualStartupService;

import javax.inject.Inject;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@MessageEndpoint("fetchLogfileMessagingGateway")
public class FetchLogfileMessagingGateway {

    private final static Logger LOGGER = LoggerFactory.getLogger(FetchLogfileMessagingGateway.class);

    @Value("${http.getRequest}")
    private String httpgetRequest;

    @Value("${http.host}")
    private String httpHost;

    @Value("${http.username}")
    private String httpUser;

    @Value("${http.password}")
    private String httpPassword;

    @Inject
    private ManualStartupService manualStartupService;

    public TriggerStartupEvent fetchFilesnames(TriggerStartupEvent e) {
        LOGGER.info("fetchFilesnames");
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(httpgetRequest);
        try {
            HttpHost host = new HttpHost(httpHost);
            httpclient.getCredentialsProvider().setCredentials(
                    new AuthScope(host),
                    new UsernamePasswordCredentials(httpUser, httpPassword));
            HttpResponse response1 = httpclient.execute(httpget);
            LOGGER.info("HTTP-StatusCode: "+response1.getStatusLine().getStatusCode());
            HttpEntity entity1 = response1.getEntity();
            InputStream instream = entity1.getContent();
            StringBuilder sb = new StringBuilder();
            byte[] buf = new byte[1024];
            int len;
            while ((len = instream.read(buf)) > 0) {
                for(int i=0;i<len;i++){
                    char c = (char) buf[i];
                    sb.append(c);
                }
            }
            e.setDirectoryContentHtml(sb.toString());
        } catch (IOException e1) {
            LOGGER.error(e1.getMessage());
        } finally {
            httpget.releaseConnection();
        }
        LOGGER.info("fetchFilesnames: "+e.toString());
        return e;
    }

    @Filter
    public boolean isHttpRequestOk(TriggerStartupEvent e) {
        return e.getDirectoryContentHtml() != null && !e.getDirectoryContentHtml().isEmpty();
    }

    private final static String pattern = "access.log.[0-9.]*.gz";

    public TriggerStartupEvent extractFilesnames(TriggerStartupEvent e) {
        List<String> filenames = new ArrayList<String>();
        String html = e.getDirectoryContentHtml();
        String[] lines = html.split("\n");
        for (String line : lines) {
            Matcher matcher = Pattern.compile(pattern).matcher(line);
            if (matcher.find()) {
                filenames.add(matcher.group().toString());
            }
        }
        e.setFilenames(filenames);
        LOGGER.info("extractFilesnames: " + e.toString());
        return e;
    }

    @Filter
    public boolean hasFilenames(TriggerStartupEvent e) {
        return e.getFilenames() != null && e.getFilenames().size() > 0;
    }

    @Aggregator
    public TriggerStartupEvent aggregateFilenames(List<Message<String>> filenames) {
        TriggerStartupEvent event = (TriggerStartupEvent) filenames.get(0).getHeaders().get("fetchFilenames");
        for (Message<String> filename : filenames) {
            LOGGER.info("aggregateFilenames: " + filename.getPayload());
        }
        return event;
    }

    @CorrelationStrategy
    public Object correlateFilenames(Message<String> filename) {
        return filename.getHeaders().get("fetchFilenames");
    }

    public boolean releaseFilenames(List<Message<String>> filenames) {
        TriggerStartupEvent event = (TriggerStartupEvent) filenames.get(0).getHeaders().get("fetchFilenames");
        List<String> listOfFilenames = new ArrayList<String>();
        for (Message<String> filename : filenames) {
            listOfFilenames.add(filename.getPayload());
        }
        return event.isSatisfiedBy(listOfFilenames);
    }

    public TriggerStartupEvent logFilename(TriggerStartupEvent e) {
        for (String filename : e.getFilenames()) {
            LOGGER.info("logFilename: " + filename);
        }
        return e;
    }

    public String logOneFilename(String filename) {
        LOGGER.info("logOneFilename: " + filename);
        return filename;
    }

    public void startPostProcssing(TriggerStartupEvent e) {
        manualStartupService.processLogfileLines();
    }
}
