package com.fastcampus.kafkahandson.ugc.port;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<PostEntity, Long> { }