package org.woehlke.logfileloader.web;

import org.junit.Test;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.woehlke.logfileloader.core.model.Matchers.isProcessingStatus;

public class HomeControllerTest extends AbstractControllerTest {

    @Test
    public void homeTest() throws Exception {
        this.mockMvc.perform(
                get("/"))
        .andExpect(view().name(containsString("home")));
    }

    @Test
    public void startTest() throws Exception {
        this.mockMvc.perform(
                get("/start"))
                .andExpect(view().name(containsString("started")));
    }

    @Test
    public void processTest() throws Exception {
        this.mockMvc.perform(
                get("/process"))
                .andExpect(view().name(containsString("process")))
                .andExpect(model().attribute("processingStatus", notNullValue()))
                .andExpect(model().attribute("processingStatus", isProcessingStatus()));
    }

    @Test
    public void processingTest() throws Exception {
        this.mockMvc.perform(
                get("/ajax/processing"))
                .andExpect(view().name(containsString("processing")))
                .andExpect(model().attribute("processingStatus", notNullValue()))
                .andExpect(model().attribute("processingStatus", isProcessingStatus()));
    }
}
