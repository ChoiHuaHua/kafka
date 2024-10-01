package com.fastcampus.kafkahandson.ugc.port;

import com.fastcampus.kafkahandson.ugc.coupon.CouponEvent;

public interface CouponEventCachePort {
    void set(CouponEvent couponEvent);

    CouponEvent get(Long id);
}
