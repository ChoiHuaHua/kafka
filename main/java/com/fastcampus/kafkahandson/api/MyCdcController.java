package com.fastcampus.kafkahandson.api;

import com.fastcampus.kafkahandson.model.MyMessage;
import com.fastcampus.kafkahandson.model.MyModel;
import com.fastcampus.kafkahandson.producer.MyProducer;
import com.fastcampus.kafkahandson.service.MyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MyCdcController {

    private final MyService myService;


    @PostMapping("/greeting")
    MyModel create(@RequestBody Request request) {
        if (request == null || request.userId == null || request.userName == null || request.userAge == null || request.content == null) {
            return null;
        }

        MyModel model = MyModel.create(request.userId, request.userAge, request.userName, request.content);

        return myService.save(model);
    }

    @GetMapping("/greeting/{id}")
    MyModel get(@PathVariable Integer id) {
        return myService.findById(id);
    }

    @PatchMapping("/greeting/{id}")
    MyModel update(@PathVariable Integer id, @RequestBody String content) {
        if(id == null || content == null || content.isEmpty()) {
            return null;
        }

        MyModel model = myService.findById(id);
        model.setContent(content);
        return myService.save(model);
    }

    @DeleteMapping("/greeting/{id}")
    void delete(@PathVariable Integer id) {
        myService.delete(id);
    }

    @Data
    private static class Request {
        Integer userId;
        String userName;
        Integer userAge;
        String content;
    }
}
