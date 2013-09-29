package org.woehlke.logfileloader.web;

import org.junit.Test;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.woehlke.logfileloader.core.dao.model.Matchers.isPageOfPageReportItem;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 29.09.13
 * Time: 12:26
 * To change this template use File | Settings | File Templates.
 */
public class ReportsUrlControllerTest extends AbstractControllerTest {


    @Test
    public void listPages() throws Exception {
        this.mockMvc.perform(
                get("/reports/listPages"))
                .andExpect(view().name(containsString("reports/listPages")))
                .andExpect(model().attribute("listPages", notNullValue()))
                .andExpect(model().attribute("listPages", isPageOfPageReportItem()));

    }

    @Test
    public void listBrowserForUrls() throws Exception {

    }
}
