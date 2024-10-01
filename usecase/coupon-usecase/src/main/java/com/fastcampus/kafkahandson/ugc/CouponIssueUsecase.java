package com.fastcampus.kafkahandson.ugc;

import com.fastcampus.kafkahandson.ugc.coupon.Coupon;

public interface CouponIssueUsecase {
    Coupon save (Long couponEventId, Long userId);
}
