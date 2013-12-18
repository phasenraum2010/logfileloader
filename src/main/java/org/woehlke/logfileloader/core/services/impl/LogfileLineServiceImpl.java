package org.woehlke.logfileloader.core.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.woehlke.logfileloader.core.dao.LogfileImportDao;
import org.woehlke.logfileloader.core.entities.LogfileLine;
import org.woehlke.logfileloader.core.repositories.*;
import org.woehlke.logfileloader.core.services.LogfileLineService;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 02.09.13
 * Time: 08:51
 * To change this template use File | Settings | File Templates.
 */
@Service
public class LogfileLineServiceImpl implements LogfileLineService {

    @Inject
    private LogfileLineRepository logfileLineRepository;

    @Inject
    private LogfileImportDao logfileImportDao;

    @Inject
    private LogfileLineItemRepository logfileLineItemRepository;

    @Inject
    private RequestRepository requestRepository;

    @Inject
    private IpRepository ipRepository;

    @Inject
    private HttpCodeRepository httpCodeRepository;

    @Inject
    private DayRepository dayRepository;

    @Inject
    private BrowserRepository browserRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public void createIfNotExists(LogfileLine logfileLine) {
        LogfileLine found = logfileLineRepository.findByLine(logfileLine.getLine());
        if (found == null) {
            logfileLineRepository.saveAndFlush(logfileLine);
        }
    }

    @Override
    public Page<LogfileLine> getNextUnprocessedLines() {
        boolean processed = false;
        Pageable pageable = new PageRequest(0, 50);
        return logfileLineRepository.findByProcessed(processed, pageable);
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public LogfileLine setProcessed(LogfileLine logfileLine) {
        logfileLine.setProcessed(true);
        return logfileLineRepository.saveAndFlush(logfileLine);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public void resetToUnProcessed() {
        logfileLineItemRepository.deleteAll();
        requestRepository.deleteAll();
        ipRepository.deleteAll();
        httpCodeRepository.deleteAll();
        dayRepository.deleteAll();
        browserRepository.deleteAll();
        logfileImportDao.setAllLogfileLinesToUnProcessed();
    }

}
