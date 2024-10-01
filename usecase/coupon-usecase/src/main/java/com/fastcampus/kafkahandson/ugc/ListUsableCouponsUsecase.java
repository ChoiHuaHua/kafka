package com.fastcampus.kafkahandson.ugc;

import com.fastcampus.kafkahandson.ugc.coupon.ResolvedCoupon;

import java.util.List;

public interface ListUsableCouponsUsecase {

    List<ResolvedCoupon> listByUserId(Long userId);
}
