package com.fastcampus.kafkahandson.api;

import com.fastcampus.kafkahandson.model.MyMessage;
import com.fastcampus.kafkahandson.producer.MyProducer;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    private final MyProducer myProducer;

    public MyController(MyProducer myProducer) {
        this.myProducer = myProducer;
    }

    @RequestMapping("/hello")
    String hello() {
        return "Hello World";
    }


    @RequestMapping("/message")
    void message(@RequestBody MyMessage myMessage) {
        myProducer.sendMessage(myMessage);
    }
}
