package org.woehlke.logfileloader.core.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.woehlke.logfileloader.core.entities.LogfileLine;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 02.09.13
 * Time: 08:59
 * To change this template use File | Settings | File Templates.
 */
public interface LogfileLineRepository extends JpaRepository<LogfileLine, Long> {

    LogfileLine findByLine(String line);

    Page<LogfileLine> findByProcessed(boolean processed, Pageable pageable);
}
