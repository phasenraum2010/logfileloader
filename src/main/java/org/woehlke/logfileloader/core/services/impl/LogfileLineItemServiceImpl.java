package org.woehlke.logfileloader.core.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.woehlke.logfileloader.core.entities.*;
import org.woehlke.logfileloader.core.repositories.*;
import org.woehlke.logfileloader.core.services.LogfileLineItemService;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 03.09.13
 * Time: 16:00
 * To change this template use File | Settings | File Templates.
 */
@Service
public class LogfileLineItemServiceImpl implements LogfileLineItemService {

    @Inject
    private BrowserRepository browserRepository;

    @Inject
    private HttpCodeRepository httpCodeRepository;

    @Inject
    private IpRepository ipRepository;

    @Inject
    private LogfileLineItemRepository logfileLineItemRepository;

    @Inject
    private RequestRepository requestRepository;

    @Inject
    private DayRepository dayRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public void save(LogfileLineItem logfileLineItem) {
        logfileLineItemRepository.saveAndFlush(logfileLineItem);
    }
}
