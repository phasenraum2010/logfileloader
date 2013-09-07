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
import org.woehlke.logfileloader.core.entities.Request;
import org.woehlke.logfileloader.core.services.ReportsService;

import javax.inject.Inject;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 04.09.13
 * Time: 12:13
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class ReportsUrlController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ReportsBrowserController.class);

    @Inject
    private ReportsService reportsService;

    @RequestMapping(value = "/reports/listPages")
    public String listPages(Model model,
                           @PageableDefaults(value = 25, pageNumber = 0) Pageable pageable) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("HTTP-Request for /reports/listPages page="+pageable.getPageNumber()+" pageSize="+pageable.getPageSize());
        }
        Page<PageReportItem> listPages = reportsService.listPages(pageable);
        model.addAttribute("listPages", listPages);
        return "reports/listPages";
    }

    @RequestMapping(value = "/reports/listPages/{urlId}/browser")
    public String listBrowserForUrls(@PathVariable long urlId, Model model) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("HTTP-Request for /reports/listPages/"+urlId+"/browser");
        }
        List<BrowserReportItem> listBrowser = reportsService.listBrowserForUrls(urlId);
        model.addAttribute("listBrowser", listBrowser);
        Request request = reportsService.findRequestById(urlId);
        model.addAttribute("request", request);
        return "reports/listBrowserForUrls";
    }
}
