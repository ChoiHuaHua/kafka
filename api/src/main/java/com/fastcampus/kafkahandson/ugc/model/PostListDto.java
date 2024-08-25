package com.fastcampus.kafkahandson.ugc.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostListDto {

    private Long id;
    private String title;
    private String userName;
    private LocalDateTime createdAt;
}
