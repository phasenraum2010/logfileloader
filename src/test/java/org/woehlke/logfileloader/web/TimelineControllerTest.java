package org.woehlke.logfileloader.web;

import org.junit.Test;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.woehlke.logfileloader.core.dao.model.Matchers.isPageOfTimelineDaysItem;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 29.09.13
 * Time: 12:58
 * To change this template use File | Settings | File Templates.
 */
public class TimelineControllerTest extends AbstractControllerTest {

    @Test
    public void listDays() throws Exception {
        this.mockMvc.perform(
                get("/reports/timelineDays"))
                .andExpect(view().name(containsString("reports/listDays")))
                .andExpect(model().attribute("listDays", notNullValue()))
                .andExpect(model().attribute("listDays", isPageOfTimelineDaysItem()));

    }
}
