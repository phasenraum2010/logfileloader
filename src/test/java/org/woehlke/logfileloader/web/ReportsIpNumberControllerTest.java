package org.woehlke.logfileloader.web;

import org.junit.Test;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.woehlke.logfileloader.core.dao.model.Matchers.isPageOfIpNumbersReportItem;
import static org.woehlke.logfileloader.core.dao.model.Matchers.isPageOfTimelineDaysItem;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 29.09.13
 * Time: 12:25
 * To change this template use File | Settings | File Templates.
 */
public class ReportsIpNumberControllerTest extends AbstractControllerTest {

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

    }
}
