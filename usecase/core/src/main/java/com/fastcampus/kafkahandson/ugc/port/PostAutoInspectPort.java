package com.fastcampus.kafkahandson.ugc.port;

import com.fastcampus.kafkahandson.ugc.inspectedpost.AutoInspectionResult;
import com.fastcampus.kafkahandson.ugc.post.model.Post;

public interface PostAutoInspectPort {
    AutoInspectionResult inspect(Post post, String categoryName);
}
