package com.fastcampus.kafkahandson.ugc.port;

import com.fastcampus.kafkahandson.ugc.inspectedpost.InspectedPost;

import java.util.List;

public interface PostSearchPort {

    void indexPost(InspectedPost post);
    void deletePost(Long id);
    List<Long> searchPostIdsByKeyword(String keyword, int pageNumber, int pageSize);
}
