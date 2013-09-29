package org.woehlke.logfileloader.web;

import org.junit.Test;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.woehlke.logfileloader.core.dao.model.Matchers.isPageOfHttpCodeReportItem;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 29.09.13
 * Time: 12:24
 * To change this template use File | Settings | File Templates.
 */
public class ReportsHttpCodeControllerTest extends AbstractControllerTest  {

    @Test
    public void listHttpCodesTest() throws Exception {
        this.mockMvc.perform(
                get("/reports/listHttpCodes"))
                .andExpect(view().name(containsString("reports/listHttpCodes")))
                .andExpect(model().attribute("listHttpCodes", notNullValue()))
                .andExpect(model().attribute("listHttpCodes", isPageOfHttpCodeReportItem()));
    }

    @Test
    public void listUrlsForHttpCodesTest() throws Exception {

    }

    @Test
    public void listBrowserForHttpCodes() throws Exception {

    }

    @Test
    public void listIpNumbersForHttpCodes() throws Exception {

    }
}
