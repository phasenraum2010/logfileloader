package org.woehlke.logfileloader.web;

import junit.framework.Assert;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.woehlke.logfileloader.core.model.IpNumbersReportItem;
import org.woehlke.logfileloader.core.services.ReportsService;

import javax.inject.Inject;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.woehlke.logfileloader.core.model.Matchers.*;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 29.09.13
 * Time: 12:25
 * To change this template use File | Settings | File Templates.
 */
public class ReportsIpNumberControllerTest extends AbstractControllerTest {

    @Inject
    private ReportsService reportsService;

    @Test
    public void listIpNumbers() throws Exception {
        this.mockMvc.perform(
                get("/reports/listIpNumbers"))
                .andExpect(view().name(containsString("reports/listIpNumbers")))
                .andExpect(model().attribute("ipNumbersReport", notNullValue()))
                .andExpect(model().attribute("ipNumbersReport", isPageOfIpNumbersReportItem()));
    }

    @Test
    public void listUrlsForIpNumber() throws Exception {
        PageRequest pageRequest = new PageRequest(1,1);
        Page<IpNumbersReportItem> ipNumbersReport = reportsService.listIpNumbers(pageRequest);
        Assert.assertTrue(ipNumbersReport.hasContent());
        for(IpNumbersReportItem ipNumbersReportItem :ipNumbersReport.getContent()){
            this.mockMvc.perform(
                    get("/reports/listIpNumbers/"+ipNumbersReportItem.getId()+"/url"))
                    .andExpect(view().name(containsString("reports/listUrlsForIpNumber")))
                    .andExpect(model().attribute("ipNumber", notNullValue()))
                    .andExpect(model().attribute("ipNumber", isIp()))
                    .andExpect(model().attribute("listPages", notNullValue()))
                    .andExpect(model().attribute("listPages", isPageOfPageReportItem()));
        }
    }
}
