package org.woehlke.logfileloader.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.woehlke.logfileloader.core.dao.model.HttpCodeReportItem;
import org.woehlke.logfileloader.core.dao.model.TimelineDaysItem;
import org.woehlke.logfileloader.core.entities.Day;
import org.woehlke.logfileloader.core.services.ReportsService;

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
    public String listDays(
            Model model,
            @PageableDefaults(value = 25, pageNumber = 0) Pageable pageable){
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("HTTP-Request for /reports/timelineDays");
        }
        Page<TimelineDaysItem> listDays = reportsService.listDays(pageable);
        model.addAttribute("listDays",listDays);
        return "reports/listDays";
    }

    @RequestMapping(value = "/reports/timelineDays/{dayId}/httpcodes")
    public String listHttpCodesForDay(
            @PathVariable long dayId,
            Model model,
            @PageableDefaults(value = 25, pageNumber = 0) Pageable pageable){
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("HTTP-Request for /reports/timelineDays/"+dayId+"/httpcodes");
        }
        Day day = reportsService.findDayById(dayId);
        model.addAttribute("day",day);
        Page<HttpCodeReportItem> listHttpCodes = reportsService.listHttpCodesForDay(dayId, pageable);
        model.addAttribute("listHttpCodes",listHttpCodes);
        return "reports/listHttpCodesForDay";
    }
}
