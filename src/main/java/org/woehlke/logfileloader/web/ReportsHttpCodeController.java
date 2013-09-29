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
import org.woehlke.logfileloader.core.dao.model.HttpCodeReportItem;
import org.woehlke.logfileloader.core.dao.model.IpNumbersReportItem;
import org.woehlke.logfileloader.core.dao.model.PageReportItem;
import org.woehlke.logfileloader.core.entities.HttpCode;
import org.woehlke.logfileloader.core.services.ReportsService;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 07.09.13
 * Time: 08:44
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class ReportsHttpCodeController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ReportsHttpCodeController.class);

    @Inject
    private ReportsService reportsService;

    @RequestMapping(value = "/reports/listHttpCodes")
    public String listHttpCodes(
            Model model,
            @PageableDefaults(value = 25, pageNumber = 0) Pageable pageable) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("HTTP-Request for /reports/listPages");
        }
        Page<HttpCodeReportItem> listHttpCodes = reportsService.listHttpCodes(pageable);
        model.addAttribute("listHttpCodes", listHttpCodes);
        return "reports/listHttpCodes";
    }

    @RequestMapping(value = "/reports/listHttpCodes/{httpCodeId}/url")
    public String listUrlsForHttpCodes(
            @PathVariable long httpCodeId,Model model,
            @PageableDefaults(value = 25, pageNumber = 0) Pageable pageable) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("HTTP-Request for /reports/listHttpCodes/"+httpCodeId+"/url");
        }
        Page<PageReportItem> listPages = reportsService.listUrlsForHttpCodes(httpCodeId,pageable);
        model.addAttribute("listPages", listPages);
        HttpCode httpCode = reportsService.findHttpCodeById(httpCodeId);
        model.addAttribute("httpCode", httpCode);
        return "reports/listUrlsForHttpCodes";
    }

    @RequestMapping(value = "/reports/listHttpCodes/{httpCodeId}/browser")
    public String listBrowserForHttpCodes(
            @PathVariable long httpCodeId,Model model,
            @PageableDefaults(value = 25, pageNumber = 0) Pageable pageable) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("HTTP-Request for /reports/listHttpCodes/"+httpCodeId+"/browser");
        }
        Page<BrowserReportItem> listBrowser = reportsService.listBrowserForHttpCodes(httpCodeId,pageable);
        model.addAttribute("listBrowser", listBrowser);
        HttpCode httpCode = reportsService.findHttpCodeById(httpCodeId);
        model.addAttribute("httpCode", httpCode);
        return "reports/listBrowserForHttpCodes";
    }

    @RequestMapping(value = "/reports/listHttpCodes/{httpCodeId}/ip")
    public String listIpNumbersForHttpCodes(
            @PathVariable long httpCodeId,Model model,
            @PageableDefaults(value = 25, pageNumber = 0) Pageable pageable) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("HTTP-Request for /reports/listHttpCodes/"+httpCodeId+"/ip");
        }
        Page<IpNumbersReportItem> ipNumbersReport =  reportsService.listIpNumbersForHttpCodes(httpCodeId,pageable);
        model.addAttribute("ipNumbersReport", ipNumbersReport);
        HttpCode httpCode = reportsService.findHttpCodeById(httpCodeId);
        model.addAttribute("httpCode", httpCode);
        return "reports/listIpNumbersForHttpCodes";
    }


}
