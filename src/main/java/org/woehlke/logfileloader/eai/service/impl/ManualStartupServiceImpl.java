package org.woehlke.logfileloader.eai.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.integration.Message;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.woehlke.logfileloader.core.entities.LogfileLine;
import org.woehlke.logfileloader.core.services.LogfileLineService;
import org.woehlke.logfileloader.eai.events.ProcessLogfileLinesEvent;
import org.woehlke.logfileloader.eai.events.TriggerStartupEvent;
import org.woehlke.logfileloader.eai.service.ManualStartupService;

import javax.inject.Inject;

@Service
public class ManualStartupServiceImpl implements ManualStartupService {


    @Autowired
    @Qualifier("manualStartupChannel")
    private QueueChannel manualStartupChannel;

    @Autowired
    @Qualifier("processLogfileLinesChannel")
    private QueueChannel processLogfileLinesChannel;

    @Inject
    private LogfileLineService logfileLineService;

    @Override
    public void start() {
        final MessagingTemplate m = new MessagingTemplate();
        TriggerStartupEvent e = new TriggerStartupEvent();
        Message<TriggerStartupEvent> message = MessageBuilder
                .withPayload(e).build();
        m.send(manualStartupChannel, message);
    }

    @Override
    public void processLogfileLines(){
        boolean goOn = true;
        final MessagingTemplate m = new MessagingTemplate();
        while(goOn){
            Page<LogfileLine> lines = logfileLineService.getNextUnprocessedLines();
            goOn = lines.hasNextPage();
            for(LogfileLine line:lines.getContent()){
                line=logfileLineService.setProcessed(line);
                ProcessLogfileLinesEvent e = new ProcessLogfileLinesEvent();
                e.setLine(line);
                Message<ProcessLogfileLinesEvent> message = MessageBuilder
                        .withPayload(e).build();
                m.send(processLogfileLinesChannel, message);
            }
        }
    }
}
