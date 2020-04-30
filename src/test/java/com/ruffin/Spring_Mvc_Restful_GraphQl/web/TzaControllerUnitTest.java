package com.ruffin.Spring_Mvc_Restful_GraphQl.web;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ruffin.Spring_Mvc_Restful_GraphQl.service.IApplicationService;
import com.ruffin.Spring_Mvc_Restful_GraphQl.service.ITicketService;

@RunWith(SpringRunner.class)
@WebMvcTest(TzaController.class)
public class TzaControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    IApplicationService applicationService;

    @MockBean
    ITicketService ticketService;

    @Test
    public void getAllApplications() throws Exception {
        mockMvc.perform(get("/tza/applications/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("[]"));

        verify(applicationService, times(1)).listApplications();
    }

    @Test
    public void getAllTickets() throws Exception {
        mockMvc.perform(get("/tza/tickets/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("[]"));

        verify(ticketService, times(1)).listTickets();
    }
}
