package com.fastcampus.kafkahandson.ugc.controller;


import com.fastcampus.kafkahandson.ugc.model.PostListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("list")
public class PostListController {

    @GetMapping("/inbox/{userId}")
    ResponseEntity<List<PostListDto>> listSubscribingPosts(@PathVariable("userId") Long userId) {
        return ResponseEntity.internalServerError().build();
    }

    @GetMapping("/search")
    ResponseEntity<List<PostListDto>> searchPosts(@RequestParam("query") String query) {
        return ResponseEntity.internalServerError().build();
    }
}
