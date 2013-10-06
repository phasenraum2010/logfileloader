package org.woehlke.logfileloader.eai.pipelines;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.Message;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.woehlke.logfileloader.eai.events.StartLogfilesImportEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 29.09.13
 * Time: 17:24
 * To change this template use File | Settings | File Templates.
 */
@ContextConfiguration("classpath:/FilenamesAggregatorTest-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class FilenamesAggregatorTest {

    @Autowired
    QueueChannel outputChannel;

    @Autowired
    QueueChannel inputChannel;

    @Test
    public void testAggregator() throws Exception {
        StartLogfilesImportEvent e = new StartLogfilesImportEvent();
        List<String> filenames = new ArrayList<String>();
        filenames.add("test01-aggregator.txt");
        filenames.add("test02-aggregator.txt");
        filenames.add("test03-aggregator.txt");
        filenames.add("test04-aggregator.txt");
        e.setFilenames(filenames);
        inputChannel.send(MessageBuilder.withPayload(e).build());
        Message<?> outMessage = outputChannel.receive();
        Assert.assertNotNull(outMessage);
        Assert.assertTrue(outMessage.getHeaders().containsKey("fetchFilenames"));
        Object out = outMessage.getPayload();
        Assert.assertTrue(out instanceof StartLogfilesImportEvent);
        StartLogfilesImportEvent result = (StartLogfilesImportEvent) out;
        List<String> filenamesResult = result.getFilenames();
        for(String filename : filenamesResult){
            Assert.assertTrue(filenames.contains(filename));
        }
        for(String filename : filenames){
            Assert.assertTrue(filenamesResult.contains(filename));
        }
        Object outHeader = outMessage.getHeaders().get("fetchFilenames");
        Assert.assertTrue(outHeader instanceof StartLogfilesImportEvent);
        StartLogfilesImportEvent outHeaderResult = (StartLogfilesImportEvent) outHeader;
        List<String> outHeaderFilenamesResult = outHeaderResult.getFilenames();
        for(String filename : outHeaderFilenamesResult){
            Assert.assertTrue(filenames.contains(filename));
        }
        for(String filename : filenames){
            Assert.assertTrue(outHeaderFilenamesResult.contains(filename));
        }
    }
}
