package com.fastcampus.kafkahandson.ugc;

public interface RequestCouponIssueUsecase {

    void queue(Long userId, Long couponEventId);
}
