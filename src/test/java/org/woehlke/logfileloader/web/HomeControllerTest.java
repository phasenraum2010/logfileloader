package org.woehlke.logfileloader.web;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.woehlke.logfileloader.core.model.Matchers.isProcessingStatus;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HomeControllerTest extends AbstractControllerTest {

    @Test
    public void test1_homeTest() throws Exception {
        LOGGER.info("HomeControllerTest.homeTest");
        this.mockMvc.perform(
                get("/"))
        .andExpect(view().name(containsString("home")));
    }

    @Test
    public void test2_startTest() throws Exception {
        LOGGER.info("HomeControllerTest.startTest");
        this.mockMvc.perform(
                get("/start"))
                .andExpect(view().name(containsString("started")));
        Thread.sleep(12000);
    }

    @Test
    public void test3_processTest() throws Exception {
        LOGGER.info("HomeControllerTest.processTest");
        this.mockMvc.perform(
                get("/process"))
                .andExpect(view().name(containsString("process")))
                .andExpect(model().attribute("processingStatus", notNullValue()))
                .andExpect(model().attribute("processingStatus", isProcessingStatus()));
        Thread.sleep(12000);
    }

    @Test
    public void test4_processingTest() throws Exception {
        LOGGER.info("HomeControllerTest.processingTest");
        this.mockMvc.perform(
                get("/ajax/processing"))
                .andExpect(view().name(containsString("processing")))
                .andExpect(model().attribute("processingStatus", notNullValue()))
                .andExpect(model().attribute("processingStatus", isProcessingStatus()));
        Thread.sleep(12000);
    }
}
