package org.woehlke.logfileloader.web;

import junit.framework.Assert;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.woehlke.logfileloader.core.model.HttpCodeReportItem;
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
 * Time: 12:24
 * To change this template use File | Settings | File Templates.
 */
public class ReportsHttpCodeControllerTest extends AbstractControllerTest  {

    @Inject
    private ReportsService reportsService;

    @Test
    public void listHttpCodesTest() throws Exception {
        this.mockMvc.perform(
                get("/reports/listHttpCodes"))
                .andExpect(view().name(containsString("reports/listHttpCodes")))
                .andExpect(model().attribute("listHttpCodes", notNullValue()))
                .andExpect(model().attribute("listHttpCodes", isPageOfHttpCodeReportItem()));
    }

    /**
     * @see org.woehlke.logfileloader.web.ReportsHttpCodeController
     * @throws Exception
     */
    @Test
    public void listUrlsForHttpCodesTest() throws Exception {
        PageRequest pageRequest = new PageRequest(1,1);
        Page<HttpCodeReportItem> listHttpCodes = reportsService.listHttpCodes(pageRequest);
        Assert.assertTrue(listHttpCodes.hasContent());
        for(HttpCodeReportItem httpCode:listHttpCodes.getContent()){
            this.mockMvc.perform(
                    get("/reports/listHttpCodes/"+httpCode.getId()+"/url"))
                    .andExpect(view().name(containsString("reports/listUrlsForHttpCodes")))
                    .andExpect(model().attribute("httpCode", notNullValue()))
                    .andExpect(model().attribute("httpCode", isHttpCode()))
                    .andExpect(model().attribute("listPages", notNullValue()))
                    .andExpect(model().attribute("listPages", isPageOfPageReportItem()));
        }
    }

    /**
     * @see org.woehlke.logfileloader.web.ReportsHttpCodeController
     * @throws Exception
     */
    @Test
    public void listBrowserForHttpCodes() throws Exception {
        PageRequest pageRequest = new PageRequest(1,1);
        Page<HttpCodeReportItem> listHttpCodes = reportsService.listHttpCodes(pageRequest);
        Assert.assertTrue(listHttpCodes.hasContent());
        for(HttpCodeReportItem httpCode:listHttpCodes.getContent()){
            this.mockMvc.perform(
                    get("/reports/listHttpCodes/"+httpCode.getId()+"/browser"))
                    .andExpect(view().name(containsString("reports/listBrowserForHttpCodes")))
                    .andExpect(model().attribute("httpCode", notNullValue()))
                    .andExpect(model().attribute("httpCode", isHttpCode()))
                    .andExpect(model().attribute("listBrowser", notNullValue()))
                    .andExpect(model().attribute("listBrowser", isPageOfBrowserReportItem()));
        }
    }

    /**
     * @see org.woehlke.logfileloader.web.ReportsHttpCodeController
     * @throws Exception
     */
    @Test
    public void listIpNumbersForHttpCodes() throws Exception {
        PageRequest pageRequest = new PageRequest(1,1);
        Page<HttpCodeReportItem> listHttpCodes = reportsService.listHttpCodes(pageRequest);
        Assert.assertTrue(listHttpCodes.hasContent());
        for(HttpCodeReportItem httpCode:listHttpCodes.getContent()){
            this.mockMvc.perform(
                    get("/reports/listHttpCodes/"+httpCode.getId()+"/ip"))
                    .andExpect(view().name(containsString("reports/listIpNumbersForHttpCodes")))
                    .andExpect(model().attribute("httpCode", notNullValue()))
                    .andExpect(model().attribute("httpCode", isHttpCode()))
                    .andExpect(model().attribute("ipNumbersReport", notNullValue()))
                    .andExpect(model().attribute("ipNumbersReport", isPageOfIpNumbersReportItem()));
        }
    }
}
