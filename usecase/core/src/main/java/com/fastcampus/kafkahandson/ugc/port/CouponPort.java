package com.fastcampus.kafkahandson.ugc.port;

import com.fastcampus.kafkahandson.ugc.coupon.Coupon;

public interface CouponPort {

    Coupon save(Coupon coupon);
}
