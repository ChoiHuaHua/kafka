package com.fastcampus.kafkahandson.ugc;

import com.fastcampus.kafkahandson.ugc.port.MetadataPort;
import com.fastcampus.kafkahandson.ugc.post.model.Post;
import com.fastcampus.kafkahandson.ugc.post.model.ResolvedPost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class PostResolvingHelpService implements PostResolvingHelpUsecase {

    private final MetadataPort metadataPort;

    @Override
    public ResolvedPost resolvePostById(Long postId) {
        ResolvedPost resolvedPost = null;
        return resolvedPost;
    }

    @Override
    public List<ResolvedPost> resolvePostsByIds(List<Long> postIds) {
        return postIds.stream().map(this::resolvePostById).toList();
    }

    @Override
    public void resolvePostAndSave(Post post) {

    }

    @Override
    public void deleteResolvedPost(Long postId) {

    }
}