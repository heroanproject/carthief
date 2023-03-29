package com.example.carthief.service;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class Publisher {

    RabbitTemplate template;
    String MESSAGE_QUEUE = "message";

    public Publisher(RabbitTemplate template) {
        this.template = template;
    }
    public void publishMessage(String message) {
        template.convertAndSend(MESSAGE_QUEUE, message);
    }

    @Bean
    public Queue messageQueue() {
        return new Queue(MESSAGE_QUEUE);
    }

    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(MESSAGE_QUEUE);
        container.setMessageListener(message -> {
                    try{
                        Thread.sleep(2000);
                } catch (InterruptedException e){
            throw new RuntimeException(e);
        }
            System.out.println("Message: " + new String(message.getBody()));
        });
        return container;
    }
}
