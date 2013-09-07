package org.woehlke.logfileloader.eai.pipelines;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.integration.Message;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.support.MessageBuilder;
import org.woehlke.logfileloader.core.entities.LogfileLine;
import org.woehlke.logfileloader.core.services.LogfileLineService;
import org.woehlke.logfileloader.eai.events.ProcessLogfileLinesEvent;
import org.woehlke.logfileloader.eai.events.TriggerProcessLogfileLinesEvent;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 07.09.13
 * Time: 10:13
 * To change this template use File | Settings | File Templates.
 */
@MessageEndpoint("triggerProcessLogfileLinesPipeline")
public class TriggerProcessLogfileLinesPipeline {


    @Autowired
    @Qualifier("processLogfileLinesChannel")
    private QueueChannel processLogfileLinesChannel;

    @Inject
    private LogfileLineService logfileLineService;

    public TriggerProcessLogfileLinesEvent processLogfileLines(TriggerProcessLogfileLinesEvent event) {
        boolean goOn = true;
        final MessagingTemplate m = new MessagingTemplate();
        while (goOn) {
            Page<LogfileLine> lines = logfileLineService.getNextUnprocessedLines();
            goOn = lines.hasNextPage();
            for (LogfileLine line : lines.getContent()) {
                line = logfileLineService.setProcessed(line);
                ProcessLogfileLinesEvent e = new ProcessLogfileLinesEvent();
                e.setLine(line);
                Message<ProcessLogfileLinesEvent> message = MessageBuilder
                        .withPayload(e).build();
                m.send(processLogfileLinesChannel, message);
            }
        }
        return event;
    }

}
