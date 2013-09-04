package org.woehlke.logfileloader.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.woehlke.logfileloader.core.dao.model.BrowserReportItem;
import org.woehlke.logfileloader.core.dao.model.IpNumbersReportItem;
import org.woehlke.logfileloader.core.dao.model.PageReportItem;
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
public class ReportsController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ReportsController.class);

    @Inject
    private ReportsService reportsService;

    @RequestMapping(value = "/reports/listIpNumbers")
    public String listIpNumbers(Model model) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("HTTP-Request for /reports/listIpNumbers");
        }
        List<IpNumbersReportItem> ipNumbersReport = reportsService.listIpNumbers();
        for (IpNumbersReportItem ipNumbersReportItem : ipNumbersReport) {
            LOGGER.info(ipNumbersReportItem.toString());
        }
        model.addAttribute("ipNumbersReport", ipNumbersReport);
        return "reports/listIpNumbers";
    }

    @RequestMapping(value = "/reports/listBrowser")
    public String listBrowser(Model model) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("HTTP-Request for /reports/listBrowser");
        }
        List<BrowserReportItem> listBrowser = reportsService.listBrowser();
        for (BrowserReportItem ipNumbersReportItem : listBrowser) {
            LOGGER.info(ipNumbersReportItem.toString());
        }
        model.addAttribute("listBrowser", listBrowser);
        return "reports/listBrowser";
    }

    @RequestMapping(value = "/reports/listPages")
    public String listPages(Model model) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("HTTP-Request for /reports/listPages");
        }
        List<PageReportItem> listPages = reportsService.listPages();
        for (PageReportItem ipNumbersReportItem : listPages) {
            LOGGER.info(ipNumbersReportItem.toString());
        }
        model.addAttribute("listPages", listPages);
        return "reports/listPages";
    }
}
