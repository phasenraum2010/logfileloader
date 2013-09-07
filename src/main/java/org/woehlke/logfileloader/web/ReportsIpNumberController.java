package org.woehlke.logfileloader.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.woehlke.logfileloader.core.dao.model.IpNumbersReportItem;
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
    public String listIpNumbers(Model model) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("HTTP-Request for /reports/listIpNumbers");
        }
        List<IpNumbersReportItem> ipNumbersReport = reportsService.listIpNumbers();
        model.addAttribute("ipNumbersReport", ipNumbersReport);
        return "reports/listIpNumbers";
    }
}
