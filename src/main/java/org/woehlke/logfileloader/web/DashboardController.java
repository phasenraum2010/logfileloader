package org.woehlke.logfileloader.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.woehlke.logfileloader.core.model.BrowserReportItem;
import org.woehlke.logfileloader.core.model.HttpCodeReportItem;
import org.woehlke.logfileloader.core.model.PageReportItem;
import org.woehlke.logfileloader.core.model.TimelineDaysItem;
import org.woehlke.logfileloader.core.services.ReportsService;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 13.09.13
 * Time: 15:25
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class DashboardController {


    private final static Logger LOGGER = LoggerFactory.getLogger(TimelineController.class);

    @Inject
    private ReportsService reportsService;

    @RequestMapping(value = "/reports/dashboard")
    public String listDays(
            Model model){
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("HTTP-Request for /reports/dashboard");
        }
        int page=0;
        int size=30;
        Pageable pageable = new PageRequest(page, size);
        Page<TimelineDaysItem> listDays = reportsService.listDays(pageable);
        model.addAttribute("listDays",listDays);
        Page<HttpCodeReportItem> listHttpCodes = reportsService.listHttpCodes(pageable);
        model.addAttribute("listHttpCodes", listHttpCodes);
        Page<PageReportItem> listPages = reportsService.listPages(pageable);
        model.addAttribute("listPages", listPages);
        Page<BrowserReportItem> listBrowser = reportsService.listBrowser(pageable);
        model.addAttribute("listBrowser", listBrowser);
        return "reports/dashboard";
    }
}
