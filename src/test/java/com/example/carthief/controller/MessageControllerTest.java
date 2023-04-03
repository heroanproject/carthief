package com.example.carthief.controller;

import com.example.carthief.service.Publisher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Copy;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = MessageController.class)
@AutoConfigureMockMvc(addFilters = false)
class MessageControllerTest {

    @Autowired
    private MessageController messageController;

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private Publisher publisher;




    @Test
    void testPostMessage () {
        String message = "test message";
        messageController.postMessage(message);
        Mockito.verify(publisher, Mockito.times(1)).publishMessage(message);
    }
    @ParameterizedTest
    @CsvSource({
            "test message",
            "test message ^&*()_+{}|:\"<>?,./;'[]\\=-`~",
            "{\"name\": \"test\", \"age\": 30}"
    })

    void testPostMessage(String message) throws Exception {
        mockMvc.perform(post("/messages").contentType(MediaType.APPLICATION_JSON).content(message))
               .andExpect(status().isOk());
        Mockito.verify(publisher, Mockito.times(1)).publishMessage(message);
    }

    @Test
    void testPostMessageWithEmptyMessage () throws Exception {
        String message = "";
        mockMvc.perform(post("/messages").contentType(MediaType.APPLICATION_JSON).content(message))
               .andExpect(status().isBadRequest());
        Mockito.verifyNoInteractions(publisher);
    }



    @Test
    void testPostMessageWithXml () throws Exception {
        String message = "<user><name>test</name><age>30</age></user>";
        mockMvc.perform(post("/messages").contentType(MediaType.APPLICATION_XML).content(message))
               .andExpect(status().isOk());
        Mockito.verify(publisher, Mockito.times(1)).publishMessage(message);
    }
}