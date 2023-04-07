package com.example.carthief.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PublisherTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private Publisher publisher;

    @Test
    public void testPublishMessage() {
        String message = "Test message";
        publisher.publishMessage(message);

        verify(rabbitTemplate, times(1)).convertAndSend(anyString(), anyString());
    }

    @Test
    public void testMessageQueue() {
        assertEquals("message", publisher.messageQueue().getName());
    }

    // Note: This test requires a running RabbitMQ server
    @Test
    public void testContainer() throws InterruptedException {
        String message = "Test message";
        publisher.publishMessage(message);

        Thread.sleep(3000); // Wait for the message to be processed

        // Verify that the message was processed
        verify(rabbitTemplate, times(1)).convertAndSend(anyString(), anyString());
    }
}

