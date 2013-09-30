package org.woehlke.logfileloader.web;

import org.junit.Test;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.woehlke.logfileloader.core.dao.model.Matchers.*;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 29.09.13
 * Time: 11:42
 * To change this template use File | Settings | File Templates.
 */
public class DashboardControllerTest extends AbstractControllerTest {

    @Test
    public void listDaysTest() throws Exception {
        this.mockMvc.perform(
                get("/reports/dashboard"))
                .andExpect(view().name(containsString("reports/dashboard")))
                .andExpect(model().attributeExists("listDays"))
                .andExpect(model().attributeExists("listHttpCodes"))
                .andExpect(model().attributeExists("listPages"))
                .andExpect(model().attributeExists("listBrowser"))
                .andExpect(model().attribute("listDays", notNullValue()))
                .andExpect(model().attribute("listDays", isPageOfTimelineDaysItem()))
                .andExpect(model().attribute("listHttpCodes", notNullValue()))
                .andExpect(model().attribute("listHttpCodes", isPageOfHttpCodeReportItem()))
                .andExpect(model().attribute("listPages", notNullValue()))
                .andExpect(model().attribute("listPages", isPageOfPageReportItem()))
                .andExpect(model().attribute("listBrowser", notNullValue()))
                .andExpect(model().attribute("listBrowser", isPageOfBrowserReportItem()));
    }
}
