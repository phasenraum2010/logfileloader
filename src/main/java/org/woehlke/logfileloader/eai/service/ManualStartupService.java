package org.woehlke.logfileloader.eai.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.woehlke.logfileloader.core.dao.model.TimelineDaysItem;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 01.09.13
 * Time: 16:32
 * To change this template use File | Settings | File Templates.
 */
public interface ManualStartupService {

    void start();

    void processLogfileLines();

}
