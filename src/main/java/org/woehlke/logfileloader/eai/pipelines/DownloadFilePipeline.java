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
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Splitter;
import org.woehlke.logfileloader.core.entities.LogfileLine;
import org.woehlke.logfileloader.core.services.LogfileLineService;
import org.woehlke.logfileloader.eai.events.ImportLogfileEvent;

import javax.inject.Inject;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

@MessageEndpoint("downloadFilePipeline")
public class DownloadFilePipeline {

    private final static Logger LOGGER = LoggerFactory.getLogger(DownloadFilePipeline.class);

    @Value("${http.getRequest}")
    private String httpgetRequest;

    @Value("${http.host}")
    private String httpHost;

    @Value("${http.username}")
    private String httpUser;

    @Value("${http.password}")
    private String httpPassword;

    @Inject
    private LogfileLineService logfileLineService;

    private String getTempDirectory(){
        if(System.getProperty("os.name").startsWith("Windows")){
            return "C:\\TEMP\\";
        }
        return "/tmp/";
    }

    public String downloadFile(String filename){
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(httpgetRequest+"/"+filename);
        try {
            HttpHost host = new HttpHost(httpHost);
            httpclient.getCredentialsProvider().setCredentials(
                    new AuthScope(host),
                    new UsernamePasswordCredentials(httpUser, httpPassword));
            HttpResponse response1 = httpclient.execute(httpget);
            HttpEntity entity1 = response1.getEntity();
            InputStream instream = entity1.getContent();
            File file = new File(getTempDirectory()+filename);
            FileOutputStream  out = new FileOutputStream(file);
            while(instream.available()>0){
                out.write(instream.read());
            }
            out.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            httpget.releaseConnection();
        }
        return filename;
    }

    public String unzip(String filename) {
        File fileOut = new File(getTempDirectory()+filename+".txt");
        File fileIn = new File(getTempDirectory()+filename);
        try {
            FileOutputStream out = new FileOutputStream(fileOut);
            GZIPInputStream in = new GZIPInputStream(new FileInputStream(fileIn));
            while(in.available()>0){
                out.write(in.read());
            }
            out.close();
            in.close();
        } catch (java.io.EOFException eofe) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileIn.delete();
        return filename;
    }

    public ImportLogfileEvent importLogfile(String filename) {
        File file = new File(getTempDirectory()+filename+".txt");
        List<String> lines = new ArrayList<String>();
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            while(fileReader.ready()){
                lines.add(fileReader.readLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        file.delete();
        ImportLogfileEvent e = new ImportLogfileEvent();
        e.setFilename(filename);
        e.setLines(lines);
        return e;
    }

    @Splitter
    public List<String> splitLogfileIntoLines(ImportLogfileEvent e){
        return e.getLines();
    }

    @Aggregator
    public String aggregateLines(List<Message<String>> lines) {
        return ((ImportLogfileEvent) lines.get(0).getHeaders().get("filename")).getFilename();
    }

    @CorrelationStrategy
    public Object correlateLines(Message<String> line) {
        return line.getHeaders().get("filename");
    }


    public boolean releaseLines(List<Message<String>> lines) {
        ImportLogfileEvent event = (ImportLogfileEvent) lines.get(0).getHeaders().get("filename");
        List<String> listOfLines = new ArrayList<String>();
        for(Message<String> line :lines){
            listOfLines.add(line.getPayload());
        }
        return event.isSatisfiedBy(listOfLines);
    }

    public String logLine(String line) {
        LOGGER.info(line);
        return line;
    }

    public String pushToDatabase(String line){
        LogfileLine logfileLine = new LogfileLine();
        logfileLine.setLine(line);
        logfileLineService.createIfNotExists(logfileLine);
        return line;
    }
}
