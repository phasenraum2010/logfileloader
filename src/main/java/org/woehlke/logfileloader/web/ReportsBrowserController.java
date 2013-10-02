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
import org.woehlke.logfileloader.core.dao.model.BrowserReportItem;
import org.woehlke.logfileloader.core.dao.model.PageReportItem;
import org.woehlke.logfileloader.core.entities.Browser;
import org.woehlke.logfileloader.core.services.ReportsService;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 07.09.13
 * Time: 08:47
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class ReportsBrowserController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ReportsBrowserController.class);

    @Inject
    private ReportsService reportsService;

    @RequestMapping(value = "/reports/listBrowser")
    public String listBrowser(
            Model model,
            @PageableDefaults(value = 25, pageNumber = 0) Pageable pageable) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("HTTP-Request for /reports/listBrowser");
        }
        Page<BrowserReportItem> listBrowser = reportsService.listBrowser(pageable);
        model.addAttribute("listBrowser", listBrowser);
        return "reports/listBrowser";
    }

    @RequestMapping(value = "/reports/listBrowser/{browserId}/url")
    public String listUrlsForBrowser(
            @PathVariable long browserId, Model model,
            @PageableDefaults(value = 25, pageNumber = 0) Pageable pageable) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("HTTP-Request for /reports/listBrowser/"+browserId+"/url");
        }
        Page<PageReportItem> listPages = reportsService.listUrlsForBrowser(browserId,pageable);
        model.addAttribute("listPages", listPages);
        Browser browser =reportsService.findBrowserById(browserId);
        model.addAttribute("browser", browser);
        return "reports/listUrlsForBrowser";
    }
}
