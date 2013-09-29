package org.woehlke.logfileloader.web;

import org.junit.Test;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class HomeControllerTest extends AbstractControllerTest {

    @Test
    public void homeTest() throws Exception {
        this.mockMvc.perform(
                get("/"))
        .andExpect(view().name(containsString("home")));
    }

    @Test
    public void startTest() throws Exception {

    }

    @Test
    public void processTest() throws Exception {

    }
}
