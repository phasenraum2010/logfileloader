package org.woehlke.logfileloader.eai.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.woehlke.logfileloader.eai.events.TriggerProcessLogfileLinesEvent;
import org.woehlke.logfileloader.eai.events.TriggerStartupEvent;
import org.woehlke.logfileloader.eai.service.ManualStartupService;

import javax.inject.Inject;

@Service
public class ManualStartupServiceImpl implements ManualStartupService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ManualStartupServiceImpl.class);

    @Autowired
    @Qualifier("manualStartupChannel")
    private QueueChannel manualStartupChannel;

    @Autowired
    @Qualifier("manualTriggerProcessLogfileLinesChannel")
    private QueueChannel manualTriggerProcessLogfileLinesChannel;

    @Override
    public void start() {
        final MessagingTemplate m = new MessagingTemplate();
        TriggerStartupEvent e = new TriggerStartupEvent();
        Message<TriggerStartupEvent> message = MessageBuilder
                .withPayload(e).build();
        LOGGER.info("about to send Message to start downloads.");
        m.send(manualStartupChannel, message);
        LOGGER.info("sent Message to start downloads.");
    }

    @Override
    public void processLogfileLines() {
        final MessagingTemplate m = new MessagingTemplate();
        TriggerProcessLogfileLinesEvent e = new TriggerProcessLogfileLinesEvent();
        Message<TriggerProcessLogfileLinesEvent> message = MessageBuilder
                .withPayload(e).build();
        m.send(manualTriggerProcessLogfileLinesChannel, message);
    }

}
