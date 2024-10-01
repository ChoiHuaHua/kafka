package com.fastcampus.kafkahandson.ugc.port;

import com.fastcampus.kafkahandson.ugc.coupon.Coupon;
import com.fastcampus.kafkahandson.ugc.coupon.ResolvedCoupon;

import java.util.List;

public interface CouponPort {

    Coupon save(Coupon coupon);

    List<ResolvedCoupon> listByUserId(Long userId);
}
