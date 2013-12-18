package org.woehlke.logfileloader.eai.service;

import org.woehlke.logfileloader.core.model.ProcessingStatus;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 01.09.13
 * Time: 16:32
 * To change this template use File | Settings | File Templates.
 */
public interface ManualStartupService {

    void startImport();

    void startPostProcessing();

    ProcessingStatus getPostProcessingStatus();

    void reset();
}
