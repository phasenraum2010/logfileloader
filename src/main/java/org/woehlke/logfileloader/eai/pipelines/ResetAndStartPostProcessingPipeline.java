package org.woehlke.logfileloader.eai.pipelines;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.woehlke.logfileloader.core.entities.LogfileLine;
import org.woehlke.logfileloader.core.services.LogfileLineService;
import org.woehlke.logfileloader.eai.events.ProcessOneLogfileLineEvent;
import org.woehlke.logfileloader.eai.events.StartPostProcessingEvent;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 07.09.13
 * Time: 10:13
 * To change this template use File | Settings | File Templates.
 */
@MessageEndpoint("triggerProcessLogfileLinesPipeline")
public class ResetAndStartPostProcessingPipeline {


    @Autowired
    @Qualifier("processLogfileLinesChannel")
    private QueueChannel processLogfileLinesChannel;

    @Inject
    private LogfileLineService logfileLineService;

    /** fetches from Database and sends it into the Queue */
    public StartPostProcessingEvent fetchFromDbAndPushToQueue(StartPostProcessingEvent event) {
        logfileLineService.resetToUnProcessed();
        boolean goOn = true;
        final MessagingTemplate m = new MessagingTemplate();
        while (goOn) {
            Page<LogfileLine> lines = logfileLineService.getNextUnprocessedLines();
            goOn = lines.hasNextPage();
            for (LogfileLine line : lines.getContent()) {
                line = logfileLineService.setProcessed(line);
                ProcessOneLogfileLineEvent e = new ProcessOneLogfileLineEvent();
                e.setLine(line);
                Message<ProcessOneLogfileLineEvent> message = MessageBuilder
                        .withPayload(e).build();
                m.send(processLogfileLinesChannel, message);
            }
        }
        return event;
    }

}
