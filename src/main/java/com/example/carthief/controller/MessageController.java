package com.example.carthief.controller;

import com.example.carthief.service.Publisher;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final Publisher publisher;

    public MessageController(Publisher publisher) {
        this.publisher = publisher;
    }

    @PostMapping
    public void postMessage(@RequestBody String message){
        publisher.publishMessage(message);

    }

}
