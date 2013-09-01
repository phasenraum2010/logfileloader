package org.woehlke.logfileloader.eai.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.Message;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.woehlke.logfileloader.eai.events.TriggerStartupEvent;
import org.woehlke.logfileloader.eai.service.ManualStartupService;

@Service
public class ManualStartupServiceImpl implements ManualStartupService {

    @Autowired
    @Qualifier("manualStartupChannel")
    private QueueChannel manualStartupChannel;

    @Override
    public void start() {
        final MessagingTemplate m = new MessagingTemplate();
        TriggerStartupEvent e = new TriggerStartupEvent();
        Message<TriggerStartupEvent> message = MessageBuilder
                .withPayload(e).build();
        m.send(manualStartupChannel, message);
    }
}
