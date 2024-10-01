package com.fastcampus.kafkahandson.ugc.coupon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Coupon {

    private Long id;
    private Long userId;
    private Long couponEventId;
    private LocalDateTime issuedAt;
    private LocalDateTime usedAt;

    public Coupon use() {
        this.usedAt = LocalDateTime.now();
        return this;
    }

    public static Coupon generate(Long userId, Long couponEventId) {
        return new Coupon(null, userId, couponEventId, LocalDateTime.now(), null);
    }
}
