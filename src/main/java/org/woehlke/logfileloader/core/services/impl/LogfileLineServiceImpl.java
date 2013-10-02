package org.woehlke.logfileloader.core.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.woehlke.logfileloader.core.entities.LogfileLine;
import org.woehlke.logfileloader.core.repositories.LogfileLineRepository;
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

    public LogfileLine setProcessed(LogfileLine logfileLine) {
        logfileLine.setProcessed(true);
        return logfileLineRepository.saveAndFlush(logfileLine);
    }
}
