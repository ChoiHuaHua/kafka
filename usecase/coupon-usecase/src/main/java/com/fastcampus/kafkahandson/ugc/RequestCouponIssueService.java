package com.fastcampus.kafkahandson.ugc;

import com.fastcampus.kafkahandson.ugc.port.CouponIssueRequestPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RequestCouponIssueService implements RequestCouponIssueUsecase {

    private final CouponIssueRequestPort couponIssueRequestPort;

    @Override
    public void queue(Long userId, Long couponEventId) {
        couponIssueRequestPort.sendMessage(userId, couponEventId);
    }
}
