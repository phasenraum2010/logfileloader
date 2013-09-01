package org.woehlke.logfileloader.eai.pipelines;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.MessageEndpoint;
import org.woehlke.logfileloader.eai.events.TriggerStartupEvent;

import java.io.*;

@MessageEndpoint("fetchLogfileMessagingGateway")
public class FetchLogfileMessagingGateway {


    private final static Logger LOGGER = LoggerFactory.getLogger(FetchLogfileMessagingGateway.class);

    public void fetchFilesnames(TriggerStartupEvent e){
        LOGGER.info("Startup Event Received.");
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet("http://www.thomas-woehlke.de/p/simulated-evolution/");
        HttpResponse response1 = null;
        try {
            response1 = httpclient.execute(httpGet);
            LOGGER.info(response1.getStatusLine().toString());
            HttpEntity entity1 = response1.getEntity();
            InputStream instream = entity1.getContent();
            LOGGER.info("-------------------------");
            BufferedReader in = new BufferedReader(new InputStreamReader(instream));
            while(in.ready()){
                LOGGER.info(in.readLine());
            }
            LOGGER.info("-------------------------");
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            httpGet.releaseConnection();
        }
    }
}
