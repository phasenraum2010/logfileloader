package org.woehlke.logfileloader.eai.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.woehlke.logfileloader.core.model.ProcessingStatus;
import org.woehlke.logfileloader.core.repositories.LogfileLineItemRepository;
import org.woehlke.logfileloader.core.repositories.LogfileLineRepository;
import org.woehlke.logfileloader.core.services.LogfileLineService;
import org.woehlke.logfileloader.eai.events.StartPostProcessingEvent;
import org.woehlke.logfileloader.eai.events.StartLogfilesImportEvent;
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

    @Inject
    private LogfileLineRepository logfileLineRepository;

    @Inject
    private LogfileLineItemRepository logfileLineItemRepository;

    @Inject
    private LogfileLineService logfileLineService;

    @Override
    public void startImport() {
        final MessagingTemplate m = new MessagingTemplate();
        StartLogfilesImportEvent e = new StartLogfilesImportEvent();
        Message<StartLogfilesImportEvent> message = MessageBuilder
                .withPayload(e).build();
        LOGGER.info("about to send Message to startImport downloads.");
        m.send(manualStartupChannel, message);
        LOGGER.info("sent Message to startImport downloads.");
    }

    @Override
    public void startPostProcessing() {
        final MessagingTemplate m = new MessagingTemplate();
        StartPostProcessingEvent e = new StartPostProcessingEvent();
        Message<StartPostProcessingEvent> message = MessageBuilder
                .withPayload(e).build();
        LOGGER.info("about to send Message to startImport postprocessing.");
        m.send(manualTriggerProcessLogfileLinesChannel, message);
        LOGGER.info("sent Message to startImport postprocessing");
    }

    @Override
    public ProcessingStatus getPostProcessingStatus() {
        long sourceLines = logfileLineRepository.countByProcessedTrue();
        long allLines = logfileLineRepository.count();
        long allTargetLineItems = logfileLineItemRepository.count();
        ProcessingStatus o = new ProcessingStatus();
        o.setSourceLinesToBeProcessed(sourceLines);
        o.setAllSourceLines(allLines);
        o.setAllTargetLineItems(allTargetLineItems);
        LOGGER.info("lines to be processed: " + sourceLines);
        LOGGER.info("total lines: " + allLines);
        LOGGER.info("target lines: "+allTargetLineItems);
        return o;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public void reset() {
        logfileLineService.resetToUnProcessed();
    }

}
