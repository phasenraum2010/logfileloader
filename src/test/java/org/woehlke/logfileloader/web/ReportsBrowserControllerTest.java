package org.woehlke.logfileloader.web;

import junit.framework.Assert;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.woehlke.logfileloader.core.model.BrowserReportItem;
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
 * Time: 12:23
 * To change this template use File | Settings | File Templates.
 */
public class ReportsBrowserControllerTest extends AbstractControllerTest {

    @Inject
    private ReportsService reportsService;

    @Test
    public void listBrowserTest() throws Exception {
        this.mockMvc.perform(
                get( "/reports/listBrowser"))
                .andExpect(view().name(containsString("reports/listBrowser")))
                .andExpect(model().attribute("listBrowser", notNullValue()))
                .andExpect(model().attribute("listBrowser", isPageOfBrowserReportItem()));
    }

    /**
     * @see
     * @throws Exception
     */
    @Test
    public void listUrlsForBrowserTest() throws Exception {
        PageRequest pageRequest = new PageRequest(0,1);
        Page<BrowserReportItem> listBrowser = reportsService.listBrowser(pageRequest);
        Assert.assertTrue(listBrowser.hasContent());
        for(BrowserReportItem browser:listBrowser.getContent()){
            this.mockMvc.perform(
                    get("/reports/listBrowser/"+browser.getId()+"/url"))
                    .andExpect(view().name(containsString("reports/listUrlsForBrowser")))
                    .andExpect(model().attribute("browser", notNullValue()))
                    .andExpect(model().attribute("browser", isBrowser()))
                    .andExpect(model().attribute("listPages", notNullValue()))
                    .andExpect(model().attribute("listPages", isPageOfPageReportItem()));
        }
    }
}
