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
import org.woehlke.logfileloader.eai.events.ImportOneLogfileEvent;

import javax.inject.Inject;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.GZIPInputStream;

@MessageEndpoint("downloadFilePipeline")
public class ImportOneLogfilePipeline {

    private final static Logger LOGGER = LoggerFactory.getLogger(ImportOneLogfilePipeline.class);

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

    protected String getTempDirectory() {
        if (System.getProperty("os.name").startsWith("Windows")) {
            return "C:"+File.separator+"TEMP"+File.separator;
        }
        return "/tmp/";
    }

    public String downloadFile(String filename) {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(httpgetRequest + "/" + filename);
        try {
            HttpHost host = new HttpHost(httpHost);
            httpclient.getCredentialsProvider().setCredentials(
                    new AuthScope(host),
                    new UsernamePasswordCredentials(httpUser, httpPassword));
            HttpResponse response1 = httpclient.execute(httpget);
            HttpEntity entity1 = response1.getEntity();
            InputStream instream = entity1.getContent();
            File file = new File(getTempDirectory() + filename);
            LOGGER.info(file.getAbsolutePath());
            FileOutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while ((len = instream.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            instream.close();
        } catch (IOException e1) {
            LOGGER.warn(e1.getMessage());
        } finally {
            httpget.releaseConnection();
        }
        return filename;
    }

    public String unzip(String filename) {
        File fileOut = new File(getTempDirectory() + filename + ".txt");
        File fileIn = new File(getTempDirectory() + filename);
        try {
            OutputStream out = new FileOutputStream(fileOut);
            GZIPInputStream in = new GZIPInputStream(new FileInputStream(fileIn));
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        } catch (IOException e) {
            LOGGER.warn(e.getMessage());
        }
        fileIn.delete();
        return filename;
    }

    public ImportOneLogfileEvent importLogfile(String filename) {
        File file = new File(getTempDirectory() + filename + ".txt");
        LOGGER.info("start importing: "+file.getAbsolutePath());
        List<String> lines = new ArrayList<String>();
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            while (fileReader.ready()) {
                lines.add(fileReader.readLine());
            }
            fileReader.close();
            file.delete();
        } catch (FileNotFoundException e) {
            LOGGER.warn(e.getMessage());
        } catch (IOException e) {
            LOGGER.warn(e.getMessage());
        }
        LOGGER.info("finishing importing: "+file.getAbsolutePath());
        ImportOneLogfileEvent e = new ImportOneLogfileEvent();
        e.setFilename(filename);
        e.setLines(lines);
        return e;
    }

    @Splitter
    public List<String> splitLogfileIntoLines(ImportOneLogfileEvent e) {
        return e.getLines();
    }

    @Aggregator
    public String aggregateLines(List<Message<Long>> lines) {
        return (String) lines.get(0).getHeaders().get("filename");
    }

    @CorrelationStrategy
    public Object correlateLines(Message<String> line) {
        return line.getHeaders().get("filename");
    }

    public boolean releaseLines(List<Message<String>> lines) {
        int sizeDone = lines.size();
        int sizeAll = (Integer) lines.get(0).getHeaders().get("sizeAll");
        boolean ok = sizeDone == sizeAll;
        LOGGER.info("releaseLines: "+ok+" "+sizeDone+" "+sizeAll);
        return ok;
    }

    public String logLine(String line) {
        //LOGGER.info(line);
        return line;
    }

    public Long pushToDatabase(String line) {
        LogfileLine logfileLine = new LogfileLine();
        logfileLine.setLine(line);
        long start = new Date().getTime();
        logfileLineService.createIfNotExists(logfileLine);
        long delta = new Date().getTime() - start;
        LOGGER.info("saved in "+delta+" ms");
        return logfileLine.getId();
    }
}
