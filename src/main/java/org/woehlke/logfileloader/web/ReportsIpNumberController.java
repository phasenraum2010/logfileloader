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
import org.woehlke.logfileloader.core.dao.model.IpNumbersReportItem;
import org.woehlke.logfileloader.core.dao.model.PageReportItem;
import org.woehlke.logfileloader.core.entities.Ip;
import org.woehlke.logfileloader.core.services.ReportsService;

import javax.inject.Inject;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 07.09.13
 * Time: 08:46
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class ReportsIpNumberController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ReportsIpNumberController.class);

    @Inject
    private ReportsService reportsService;

    @RequestMapping(value = "/reports/listIpNumbers")
    public String listIpNumbers(
            Model model,
            @PageableDefaults(value = 25, pageNumber = 0) Pageable pageable) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("HTTP-Request for /reports/listIpNumbers");
        }
        Page<IpNumbersReportItem> ipNumbersReport = reportsService.listIpNumbers(pageable);
        model.addAttribute("ipNumbersReport", ipNumbersReport);
        return "reports/listIpNumbers";
    }

    @RequestMapping(value = "/reports/listIpNumbers/{ipNumberId}/url")
    public String listUrlsForIpNumber(
            @PathVariable long ipNumberId,Model model,
            @PageableDefaults(value = 25, pageNumber = 0) Pageable pageable) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("HTTP-Request for /reports/listIpNumbers/"+ipNumberId+"/url");
        }
        Page<PageReportItem> listPages = reportsService.listUrlsForIpNumber(ipNumberId,pageable);
        model.addAttribute("listPages", listPages);
        Ip ipNumber = reportsService.findIpById(ipNumberId);
        model.addAttribute("ipNumber", ipNumber);
        return "reports/listUrlsForIpNumber";
    }
}
