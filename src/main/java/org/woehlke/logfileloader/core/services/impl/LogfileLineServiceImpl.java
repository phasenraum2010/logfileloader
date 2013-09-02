package org.woehlke.logfileloader.core.services.impl;

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
    @Transactional(propagation= Propagation.REQUIRES_NEW, readOnly=false)
    public void createIfNotExists(LogfileLine logfileLine) {
        LogfileLine found = logfileLineRepository.findByLine(logfileLine.getLine());
        if(found==null){
            logfileLineRepository.saveAndFlush(logfileLine);
        }
    }
}
