package org.woehlke.logfileloader.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.woehlke.logfileloader.core.dao.model.TimelineDaysItem;
import org.woehlke.logfileloader.core.services.ReportsService;
import org.woehlke.logfileloader.eai.service.ManualStartupService;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: Fert
 * Date: 09.09.13
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class TimelineController {

    private final static Logger LOGGER = LoggerFactory.getLogger(TimelineController.class);

    @Inject
    private ReportsService reportsService;

    @RequestMapping(value = "/reports/timelineDays")
    public String getDays(
            Model model,
            @PageableDefaults(value = 25, pageNumber = 0) Pageable pageable){
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("HTTP-Request for /reports/timelineDays");
        }
        Page<TimelineDaysItem> timelineDays = reportsService.getTimelineDays(pageable);
        model.addAttribute("timelineDays",timelineDays);
        return "reports/timelineDays";
    }
}
