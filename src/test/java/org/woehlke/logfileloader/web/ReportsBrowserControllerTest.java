package org.woehlke.logfileloader.web;

import org.junit.Test;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.woehlke.logfileloader.core.dao.model.Matchers.isPageOfBrowserReportItem;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 29.09.13
 * Time: 12:23
 * To change this template use File | Settings | File Templates.
 */
public class ReportsBrowserControllerTest extends AbstractControllerTest {

    @Test
    public void listBrowserTest() throws Exception {
        this.mockMvc.perform(
                get( "/reports/listBrowser"))
                .andExpect(view().name(containsString("reports/listBrowser")))
                .andExpect(model().attribute("listBrowser", notNullValue()))
                .andExpect(model().attribute("listBrowser", isPageOfBrowserReportItem()));
    }

    @Test
    public void listUrlsForBrowserTest() throws Exception {

    }
}
