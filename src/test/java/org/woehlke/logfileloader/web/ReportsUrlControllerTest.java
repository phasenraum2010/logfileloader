package org.woehlke.logfileloader.web;

import junit.framework.Assert;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.woehlke.logfileloader.core.model.PageReportItem;
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
 * Time: 12:26
 * To change this template use File | Settings | File Templates.
 */
public class ReportsUrlControllerTest extends AbstractControllerTest {

    @Inject
    private ReportsService reportsService;

    /**
     * @see org.woehlke.logfileloader.web.ReportsUrlController
     * @throws Exception
     */
    @Test
    public void listPages() throws Exception {
        this.mockMvc.perform(
                get("/reports/listPages"))
                .andExpect(view().name(containsString("reports/listPages")))
                .andExpect(model().attribute("listPages", notNullValue()))
                .andExpect(model().attribute("listPages", isPageOfPageReportItem()));

    }

    /**
     * @see org.woehlke.logfileloader.web.ReportsUrlController
     * @throws Exception
     */
    @Test
    public void listBrowserForUrls() throws Exception {
        PageRequest pageRequest = new PageRequest(1,1);
        Page<PageReportItem> listPages = reportsService.listPages(pageRequest);
        Assert.assertTrue(listPages.hasContent());
        for(PageReportItem pageItem:listPages.getContent()){
            this.mockMvc.perform(
                    get("/reports/listPages/"+pageItem.getId()+"/browser"))
                    .andExpect(view().name(containsString("reports/listBrowserForUrls")))
                    .andExpect(model().attribute("request", notNullValue()))
                    .andExpect(model().attribute("request", isRequest()))
                    .andExpect(model().attribute("listBrowser", notNullValue()))
                    .andExpect(model().attribute("listBrowser", isPageOfBrowserReportItem()));
        }
    }
}
