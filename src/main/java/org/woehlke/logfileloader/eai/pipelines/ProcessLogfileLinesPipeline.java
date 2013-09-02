package org.woehlke.logfileloader.eai.pipelines;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.MessageEndpoint;
import org.woehlke.logfileloader.eai.events.ProcessLogfileLinesEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Fert
 * Date: 02.09.13
 * Time: 14:10
 * To change this template use File | Settings | File Templates.
 */
@MessageEndpoint("processLogfileLinesPipeline")
public class ProcessLogfileLinesPipeline {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProcessLogfileLinesPipeline.class);

    public ProcessLogfileLinesEvent log(ProcessLogfileLinesEvent event){
        LOGGER.info(event.toString());
        return event;
    }
}
