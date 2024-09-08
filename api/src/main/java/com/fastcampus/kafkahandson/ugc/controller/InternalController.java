package com.fastcampus.kafkahandson.ugc.controller;

import com.fastcampus.kafkahandson.ugc.chatgpt.TestChatGptAdapter;
import com.fastcampus.kafkahandson.ugc.port.TestChatGptPort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/internal")
public class InternalController {

    private final TestChatGptPort testChatGptPort;

    @GetMapping
    public String test(@RequestParam("content") String content) {
        return testChatGptPort.test(content);
    }
}
