package com.example.carthief.service;

import com.example.carthief.dto.DealerDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.junit.jupiter.api.Assertions;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PublisherTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private Publisher publisher;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        publisher = new Publisher(rabbitTemplate, objectMapper);
    }

    @Test
    public void testMessageQueue2() {
        assertEquals("search", publisher.messageQueue().getName());
    }

    @Test
    public void testMessageQueue() {
        assertEquals("search", publisher.messageQueue().getName());
    }
    @Test
    public void publishSearchQueue() throws Exception {

        List<DealerDto> dealerList = Collections.singletonList(new DealerDto());
        String expectedJson = "[{\"name\":null,\"address\":null}]";

        when(objectMapper.writeValueAsString(dealerList)).thenReturn(expectedJson);
        when(rabbitTemplate.convertSendAndReceive(any(), any(Message.class)))
                .thenReturn(new Message("response".getBytes(), new MessageProperties()));

        publisher.publishSearchQueue(dealerList);

        ArgumentCaptor<Message> messageCaptor = ArgumentCaptor.forClass(Message.class);
        verify(rabbitTemplate).convertSendAndReceive(eq("search"), messageCaptor.capture());

        Message actualMessage = messageCaptor.getValue();
        Assertions.assertEquals(expectedJson, new String(actualMessage.getBody()));

    }



}

