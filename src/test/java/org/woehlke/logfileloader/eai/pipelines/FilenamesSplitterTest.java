package org.woehlke.logfileloader.eai.pipelines;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.Message;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import org.woehlke.logfileloader.eai.events.TriggerStartupEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 29.09.13
 * Time: 15:01
 * To change this template use File | Settings | File Templates.
 */
@ContextConfiguration("classpath:/FilenamesSplitterTest-context.xml")    // default context name is <ClassName>-context.xml
@RunWith(SpringJUnit4ClassRunner.class)
public class FilenamesSplitterTest {

    @Autowired
    QueueChannel outputChannel;

    @Autowired
    QueueChannel testChannel;

    @Test
    public void splitterTest() throws Exception {
        TriggerStartupEvent e = new TriggerStartupEvent();
        List<String> filenames = new ArrayList<String>();
        filenames.add("test01.txt");
        filenames.add("test02.txt");
        filenames.add("test03.txt");
        filenames.add("test04.txt");
        e.setFilenames(filenames);
        testChannel.send(MessageBuilder.withPayload(e).build());
        for(int i=0;i<4;i++){
            Message<?> outMessage = outputChannel.receive();
            Object payload = outMessage.getPayload();
            Assert.isTrue(payload instanceof String);
            String filename =  (String) payload;
            Assert.isTrue(filename.startsWith("test"));
            Assert.isTrue(filename.endsWith(".txt"));
        }
    }
}
