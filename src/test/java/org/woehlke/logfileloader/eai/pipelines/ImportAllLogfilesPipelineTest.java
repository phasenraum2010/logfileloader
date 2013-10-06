package org.woehlke.logfileloader.eai.pipelines;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.Message;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.woehlke.logfileloader.eai.events.StartLogfilesImportEvent;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 01.09.13
 * Time: 19:32
 * To change this template use File | Settings | File Templates.
 */
@ContextConfiguration("classpath:/FetchLogfileMessagingGatewayTest-context.xml")    // default context name is <ClassName>-context.xml
@RunWith(SpringJUnit4ClassRunner.class)
public class ImportAllLogfilesPipelineTest {

   @Autowired
   QueueChannel outputChannel;

   @Autowired
   QueueChannel inputChannel;

   private final static Logger LOGGER = LoggerFactory.getLogger(ImportAllLogfilesPipelineTest.class);

   private final String html = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 3.2 Final//EN\">\n" +
            "<html>\n" +
            " <head>\n" +
            "  <title>Index of /logs</title>\n" +
            " </head>\n" +
            " <body>\n" +
            "<h1>Index of /logs</h1>\n" +
            "<pre><img src=\"/spicons/blank.gif\" alt=\"Icon \"> <a href=\"?C=N;O=D\">Name</a>                    <a href=\"?C=M;O=A\">Last modified</a>      <a href=\"?C=S;O=A\">Size</a>  <a href=\"?C=D;O=A\">Description</a><hr><img src=\"/spicons/back.gif\" alt=\"[DIR]\"> <a href=\"/\">Parent Directory</a>                             -   \n" +
            "<img src=\"/spicons/compressed.gif\" alt=\"[CMP]\"> <a href=\"access.log.27.gz\">access.log.27.gz</a>        08-Jul-2013 00:01   13K  \n" +
            "<img src=\"/spicons/compressed.gif\" alt=\"[CMP]\"> <a href=\"access.log.28.gz\">access.log.28.gz</a>        14-Jul-2013 23:52   19K  \n" +
            "<img src=\"/spicons/compressed.gif\" alt=\"[CMP]\"> <a href=\"access.log.29.gz\">access.log.29.gz</a>        22-Jul-2013 00:00   15K  \n" +
            "<img src=\"/spicons/compressed.gif\" alt=\"[CMP]\"> <a href=\"access.log.30.gz\">access.log.30.gz</a>        28-Jul-2013 23:56   53K  \n" +
            "<img src=\"/spicons/compressed.gif\" alt=\"[CMP]\"> <a href=\"access.log.31.gz\">access.log.31.gz</a>        04-Aug-2013 23:57   55K  \n" +
            "<img src=\"/spicons/compressed.gif\" alt=\"[CMP]\"> <a href=\"access.log.32.gz\">access.log.32.gz</a>        11-Aug-2013 23:44   68K  \n" +
            "<img src=\"/spicons/compressed.gif\" alt=\"[CMP]\"> <a href=\"access.log.33.gz\">access.log.33.gz</a>        18-Aug-2013 23:54   45K  \n" +
            "<img src=\"/spicons/compressed.gif\" alt=\"[CMP]\"> <a href=\"access.log.34.1.gz\">access.log.34.1.gz</a>      20-Aug-2013 00:00   17K  \n" +
            "<img src=\"/spicons/compressed.gif\" alt=\"[CMP]\"> <a href=\"access.log.34.2.gz\">access.log.34.2.gz</a>      21-Aug-2013 00:00   17K  \n" +
            "<img src=\"/spicons/compressed.gif\" alt=\"[CMP]\"> <a href=\"access.log.34.3.gz\">access.log.34.3.gz</a>      21-Aug-2013 23:55  5.7K  \n" +
            "<img src=\"/spicons/compressed.gif\" alt=\"[CMP]\"> <a href=\"access.log.34.4.gz\">access.log.34.4.gz</a>      22-Aug-2013 23:49  5.5K  \n" +
            "<img src=\"/spicons/compressed.gif\" alt=\"[CMP]\"> <a href=\"access.log.34.5.gz\">access.log.34.5.gz</a>      24-Aug-2013 00:02   14K  \n" +
            "<img src=\"/spicons/compressed.gif\" alt=\"[CMP]\"> <a href=\"access.log.34.6.gz\">access.log.34.6.gz</a>      25-Aug-2013 00:01  6.3K  \n" +
            "<img src=\"/spicons/compressed.gif\" alt=\"[CMP]\"> <a href=\"access.log.34.7.gz\">access.log.34.7.gz</a>      26-Aug-2013 00:02   17K  \n" +
            "<img src=\"/spicons/compressed.gif\" alt=\"[CMP]\"> <a href=\"access.log.35.1.gz\">access.log.35.1.gz</a>      26-Aug-2013 23:57   14K  \n" +
            "<img src=\"/spicons/compressed.gif\" alt=\"[CMP]\"> <a href=\"access.log.35.2.gz\">access.log.35.2.gz</a>      27-Aug-2013 23:59   12K  \n" +
            "<img src=\"/spicons/compressed.gif\" alt=\"[CMP]\"> <a href=\"access.log.35.3.gz\">access.log.35.3.gz</a>      28-Aug-2013 23:56   14K  \n" +
            "<img src=\"/spicons/compressed.gif\" alt=\"[CMP]\"> <a href=\"access.log.35.4.gz\">access.log.35.4.gz</a>      29-Aug-2013 23:40   11K  \n" +
            "<img src=\"/spicons/compressed.gif\" alt=\"[CMP]\"> <a href=\"access.log.35.5.gz\">access.log.35.5.gz</a>      30-Aug-2013 23:47  8.7K  \n" +
            "<img src=\"/spicons/compressed.gif\" alt=\"[CMP]\"> <a href=\"access.log.35.6.gz\">access.log.35.6.gz</a>      31-Aug-2013 23:53  4.9K  \n" +
            "<img src=\"/spicons/unknown.gif\" alt=\"[   ]\"> <a href=\"access.log.35.7\">access.log.35.7</a>         01-Sep-2013 18:58   54K  \n" +
            "<img src=\"/spicons/unknown.gif\" alt=\"[   ]\"> <a href=\"access.log.current\">access.log.current</a>      01-Sep-2013 18:58   54K  \n" +
            "<img src=\"/spicons/unknown.gif\" alt=\"[   ]\"> <a href=\"ftp.log\">ftp.log</a>                 29-Aug-2013 22:30  580K  \n" +
            "<img src=\"/spicons/compressed.gif\" alt=\"[CMP]\"> <a href=\"ftp.log.28.gz\">ftp.log.28.gz</a>           12-Jul-2013 12:16  4.4K  \n" +
            "<img src=\"/spicons/compressed.gif\" alt=\"[CMP]\"> <a href=\"ftp.log.29.gz\">ftp.log.29.gz</a>           15-Jul-2013 11:41  1.8K  \n" +
            "<img src=\"/spicons/compressed.gif\" alt=\"[CMP]\"> <a href=\"ftp.log.30.gz\">ftp.log.30.gz</a>           26-Jul-2013 08:43  7.6K  \n" +
            "<img src=\"/spicons/compressed.gif\" alt=\"[CMP]\"> <a href=\"ftp.log.31.gz\">ftp.log.31.gz</a>           04-Aug-2013 23:33  8.3K  \n" +
            "<img src=\"/spicons/compressed.gif\" alt=\"[CMP]\"> <a href=\"ftp.log.32.gz\">ftp.log.32.gz</a>           10-Aug-2013 17:33   16K  \n" +
            "<img src=\"/spicons/compressed.gif\" alt=\"[CMP]\"> <a href=\"ftp.log.33.gz\">ftp.log.33.gz</a>           18-Aug-2013 23:20   46K  \n" +
            "<img src=\"/spicons/compressed.gif\" alt=\"[CMP]\"> <a href=\"ftp.log.34.gz\">ftp.log.34.gz</a>           25-Aug-2013 23:15  109K  \n" +
            "<img src=\"/spicons/text.gif\" alt=\"[TXT]\"> <a href=\"last_traffic\">last_traffic</a>            01-Sep-2013 00:10    0   \n" +
            "<img src=\"/spicons/unknown.gif\" alt=\"[   ]\"> <a href=\"traffic.db\">traffic.db</a>              01-Sep-2013 00:10   48K  \n" +
            "<img src=\"/spicons/layout.gif\" alt=\"[DIR]\"> <a href=\"traffic.html/\">traffic.html/</a>           01-Sep-2013 00:15    -   \n" +
            "<hr></pre>\n" +
            "</body></html>\n" +
            "\n";

    @Test
    public void extractFilesnamesTest(){
        StartLogfilesImportEvent e = new StartLogfilesImportEvent();
        e.setDirectoryContentHtml(html);
        ImportAllLogfilesPipeline gw = new ImportAllLogfilesPipeline();
        e=gw.extractFilesnames(e);
        List<String> filenames = e.getFilenames();
        for(String filename:filenames){
            LOGGER.info(filename);
        }
    }

    @Test
    public void fetchFilenamesAndExtractFilesnamesTest(){
        StartLogfilesImportEvent e = new StartLogfilesImportEvent();
        inputChannel.send(MessageBuilder.withPayload(e).build());
        Message<?> outMessage = outputChannel.receive();
        Object out =  outMessage.getPayload();
        Assert.assertNotNull(out);
        Assert.assertTrue(out instanceof StartLogfilesImportEvent);
        StartLogfilesImportEvent result = (StartLogfilesImportEvent) out;
        String html = result.getDirectoryContentHtml();
        LOGGER.info(html);
        for(String filename:result.getFilenames()){
            LOGGER.info(filename);
            Assert.assertTrue(filename.startsWith("access.log."));
            Assert.assertTrue(filename.endsWith(".gz"));
            Assert.assertTrue(html.contains(filename));
        }
    }
}
