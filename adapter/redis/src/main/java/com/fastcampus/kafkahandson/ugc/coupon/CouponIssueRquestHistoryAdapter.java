package com.fastcampus.kafkahandson.ugc.coupon;

import com.fastcampus.kafkahandson.ugc.port.CouponIssueRequestHistoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@RequiredArgsConstructor
@Component
public class CouponIssueRquestHistoryAdapter implements CouponIssueRequestHistoryPort {

    private static final String USER_REQUEST_HISTORY_KEY_PREFIX = "coupon_history.user_request.v1:";
    private static final String REQUEST_COUNT_HISTORY_KEY_PREFIX = "coupon_history.request_count.v1:";
    private static final Long EXPIRE_SECONDS = 60L * 60L * 24L * 7L;

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean setHistoryIfNotExist(Long couponEventId, Long userId) {
        return redisTemplate.opsForValue().setIfAbsent(
                this.generateUserRequestHistoryCacheKey(couponEventId, userId),
                "1",
                Duration.ofSeconds(EXPIRE_SECONDS));
    }

    @Override
    public Long getRequestSequentialNumber(Long couponEventId) {
        String key = this.generateRequestCountHistoryCacheKey(couponEventId);
        Long requestSequentialNumber = redisTemplate.opsForValue().increment(key);

        if(requestSequentialNumber != null && requestSequentialNumber == 1) {
            redisTemplate.expire(key, Duration.ofSeconds(EXPIRE_SECONDS));
        }
        return requestSequentialNumber;
    }

    private String generateUserRequestHistoryCacheKey(Long couponEventId, Long userId) {
        return USER_REQUEST_HISTORY_KEY_PREFIX + couponEventId + ":" + userId;
    }

    private String generateRequestCountHistoryCacheKey(Long couponEventId) {
        return REQUEST_COUNT_HISTORY_KEY_PREFIX + couponEventId;
    }
}
