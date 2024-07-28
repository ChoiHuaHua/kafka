package com.fastcampus.kafkahandson.api;

import com.fastcampus.kafkahandson.model.MyMessage;
import com.fastcampus.kafkahandson.producer.MyProducer;
import com.fastcampus.kafkahandson.producer.MySecondProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MyController {

    private final MyProducer myProducer;
    private final MySecondProducer mySecondProducer;

    @RequestMapping("/hello")
    String hello() {
        return "Hello World";
    }


    @RequestMapping("/message")
    void message(@RequestBody MyMessage myMessage) {
        myProducer.sendMessage(myMessage);
    }

    @RequestMapping("/second-message/{key}")
    void message(@PathVariable String key, @RequestBody String message) {
        mySecondProducer.sendMessage(key, message);
    }
}
