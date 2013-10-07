package org.woehlke.logfileloader.core.services;

import org.springframework.data.domain.Page;
import org.woehlke.logfileloader.core.entities.LogfileLine;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 02.09.13
 * Time: 08:52
 * To change this template use File | Settings | File Templates.
 */
public interface LogfileLineService {

    void createIfNotExists(LogfileLine logfileLine);

    Page<LogfileLine> getNextUnprocessedLines();

    LogfileLine setProcessed(LogfileLine logfileLine);

    void resetToUnProcessed();
}
