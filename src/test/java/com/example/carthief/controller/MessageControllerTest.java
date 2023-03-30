package com.example.carthief.controller;

import com.example.carthief.service.Publisher;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = MessageController.class)
public class MessageControllerTest {

    @Autowired
    private MessageController messageController;

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private Publisher publisher;


    @Test
    public void testPostMessage () {
        String message = "test message";
        messageController.postMessage(message);
        Mockito.verify(publisher, Mockito.times(1)).publishMessage(message);
    }

    @Test
    public void testPostMessageWithValidMessage () throws Exception {
        String message = "test message";
        mockMvc.perform(post("/messages").contentType(MediaType.APPLICATION_JSON).content(message))
               .andExpect(status().isOk());
        Mockito.verify(publisher, Mockito.times(1)).publishMessage(message);
    }

    @Test
    public void testPostMessageWithEmptyMessage () throws Exception {
        String message = "";
        mockMvc.perform(post("/messages").contentType(MediaType.APPLICATION_JSON).content(message))
               .andExpect(status().isBadRequest());
        Mockito.verifyNoInteractions(publisher);
    }

    @Test
    public void testPostMessageWithSpecialCharacters () throws Exception {
        String message = "test message ^&*()_+{}|:\"<>?,./;'[]\\=-`~";
        mockMvc.perform(post("/messages").contentType(MediaType.APPLICATION_JSON).content(message))
               .andExpect(status().isOk());
        Mockito.verify(publisher, Mockito.times(1)).publishMessage(message);
    }


    @Test
    public void testPostMessageWithJson () throws Exception {
        String message = "{\"name\": \"test\", \"age\": 30}";
        mockMvc.perform(post("/messages").contentType(MediaType.APPLICATION_JSON).content(message))
               .andExpect(status().isOk());
        Mockito.verify(publisher, Mockito.times(1)).publishMessage(message);
    }

    @Test
    public void testPostMessageWithXml () throws Exception {
        String message = "<user><name>test</name><age>30</age></user>";
        mockMvc.perform(post("/messages").contentType(MediaType.APPLICATION_XML).content(message))
               .andExpect(status().isOk());
        Mockito.verify(publisher, Mockito.times(1)).publishMessage(message);
    }
}