package com.example.carthief.service;

import com.example.carthief.dto.DealerDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Publisher {

    RabbitTemplate template;
    String SEARCH_QUEUE = "search";
    ObjectMapper objectMapper;

    public Publisher(RabbitTemplate template, ObjectMapper objectMapper) {
        this.template = template;
        this.objectMapper = objectMapper;
    }
    public void publishSearchQueue(List<DealerDto> list) {
        try {
            Message message = getAsJson(objectMapper.writeValueAsString(list));
            template.convertSendAndReceive(SEARCH_QUEUE, message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public Queue messageQueue() {
        return new Queue(SEARCH_QUEUE);
    }

    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(SEARCH_QUEUE);
        container.setMessageListener(message -> new String(message.getBody()));
        return container;
    }

    private static Message getAsJson(String json){
        return MessageBuilder
                .withBody(json.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .build();
    }
}
