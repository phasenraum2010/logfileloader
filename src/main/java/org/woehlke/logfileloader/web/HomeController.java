package org.woehlke.logfileloader.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.woehlke.logfileloader.core.model.ProcessingStatus;
import org.woehlke.logfileloader.eai.service.ManualStartupService;

import javax.inject.Inject;

@Controller
public class HomeController {

    private final static Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @Inject
    private ManualStartupService manualStartupService;

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/")
    public String home(Model model) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("HTTP-Request for /");
        }
        return "home";
    }

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/start")
    public String start(Model model) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("HTTP-Request for /startImport");
        }
        manualStartupService.startImport();
        return "started";
    }

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/process")
    public String process(Model model) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("HTTP-Request for /process");
        }
        manualStartupService.startPostProcessing();
        ProcessingStatus processingStatus = manualStartupService.getPostProcessingStatus();
        model.addAttribute("processingStatus",processingStatus);
        return "process";
    }

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/ajax/processing")
    public String processing(Model model) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("HTTP-Request for /ajax/processing");
        }
        ProcessingStatus processingStatus = manualStartupService.getPostProcessingStatus();
        model.addAttribute("processingStatus",processingStatus);
        return "processing";
    }
}
