package com.fastcampus.kafkahandson.ugc.port;

public interface CouponIssueRequestHistoryPort {

    boolean setHistoryIfNotExist(Long couponEventId, Long userId);
    Long getRequestSequentialNumber(Long couponEventId);
}
