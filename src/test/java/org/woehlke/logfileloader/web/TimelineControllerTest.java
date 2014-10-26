package org.woehlke.logfileloader.web;

import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.woehlke.logfileloader.core.model.TimelineDaysItem;
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
 * Time: 12:58
 * To change this template use File | Settings | File Templates.
 */
public class TimelineControllerTest extends AbstractControllerTest {

    @Inject
    private ReportsService reportsService;

    @Test
    public void listDays() throws Exception {
        this.mockMvc.perform(
                get("/reports/timelineDays"))
                .andExpect(view().name(containsString("reports/listDays")))
                .andExpect(model().attribute("listDays", notNullValue()))
                .andExpect(model().attribute("listDays", isPageOfTimelineDaysItem()));
    }

    @Test
    public void listHttpCodesForDay() throws Exception {
        int page = 0;
        int size = 1;
        PageRequest pageRequest = new PageRequest(page,size);
        Page<TimelineDaysItem> listDays = reportsService.listDays(pageRequest);
        Assert.assertTrue(listDays.getContent().size()>0);
        for(TimelineDaysItem daysItem:listDays.getContent()) {
            LOGGER.info(daysItem.toString());
                this.mockMvc.perform(
                        get("/reports/timelineDays/" + daysItem.getId() + "/httpcodes"))
                        .andExpect(view().name(containsString("reports/listHttpCodesForDay")))
                        .andExpect(model().attribute("day", notNullValue()))
                        .andExpect(model().attribute("day", isDay()))
                        .andExpect(model().attribute("listHttpCodes", notNullValue()))
                        .andExpect(model().attribute("listHttpCodes", isPageOfHttpCodeReportItem()));
        }
    }

    @Test
    public void listUrlsForDay() throws Exception {
        PageRequest pageRequest = new PageRequest(0,1);
        Page<TimelineDaysItem> listDays = reportsService.listDays(pageRequest);
        Assert.assertTrue(listDays.hasContent());
        for(TimelineDaysItem daysItem:listDays.getContent()){
            this.mockMvc.perform(
                    get("/reports/timelineDays/"+daysItem.getId()+"/url"))
                    .andExpect(view().name(containsString("reports/listUrlsForDay")))
                    .andExpect(model().attribute("day", notNullValue()))
                    .andExpect(model().attribute("day", isDay()))
                    .andExpect(model().attribute("listPages", notNullValue()))
                    .andExpect(model().attribute("listPages", isPageOfPageReportItem()));
        }
    }

    @Test
    public void listBrowserForDay() throws Exception {
        PageRequest pageRequest = new PageRequest(0,1);
        Page<TimelineDaysItem> listDays = reportsService.listDays(pageRequest);
        Assert.assertTrue(listDays.hasContent());
        for(TimelineDaysItem daysItem:listDays.getContent()){
            this.mockMvc.perform(
                    get("/reports/timelineDays/"+daysItem.getId()+"/browser"))
                    .andExpect(view().name(containsString("reports/listBrowserForDay")))
                    .andExpect(model().attribute("day", notNullValue()))
                    .andExpect(model().attribute("day", isDay()))
                    .andExpect(model().attribute("listBrowser", notNullValue()))
                    .andExpect(model().attribute("listBrowser", isPageOfBrowserReportItem()));
        }
    }
}
