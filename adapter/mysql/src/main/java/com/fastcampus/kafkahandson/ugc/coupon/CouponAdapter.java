package com.fastcampus.kafkahandson.ugc.coupon;

import com.fastcampus.kafkahandson.ugc.port.CouponPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class CouponAdapter implements CouponPort {

    private final CouponJpaRepository couponJpaRepository;

    @Override
    public Coupon save(Coupon coupon) {
        CouponEntity couponEntity = couponJpaRepository.save(CouponEntityConverter.toCouponEntity(coupon));
        return CouponEntityConverter.toCouponModel(couponEntity);
    }

    @Override
    public List<ResolvedCoupon> listByUserId(Long userId) {
        return couponJpaRepository.findAllByUserId(userId).stream()
                .map(CouponEntityConverter::toResolvedCouponModel)
                .toList();
    }
}
