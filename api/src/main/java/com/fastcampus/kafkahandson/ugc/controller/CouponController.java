package com.fastcampus.kafkahandson.ugc.controller;

import com.fastcampus.kafkahandson.ugc.CouponIssueHistoryUsecase;
import com.fastcampus.kafkahandson.ugc.RequestCouponIssueUsecase;
import com.fastcampus.kafkahandson.ugc.model.CouponIssueRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/coupons")
public class CouponController {

    private final CouponIssueHistoryUsecase couponIssueHistoryUsecase;
    private final RequestCouponIssueUsecase requestCouponIssueUsecase;

    @PostMapping
    ResponseEntity<String> issue(@RequestBody CouponIssueRequest request) {
        if (!couponIssueHistoryUsecase.isFirstRequestFromUser(request.getCouponEventId(), request.getUserId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("already used coupon");
        }

        if (!couponIssueHistoryUsecase.hasRemainingCoupon(request.getCouponEventId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Not enough available coupons");
        }

        requestCouponIssueUsecase.queue(request.getUserId(), request.getCouponEventId());
        return ResponseEntity.ok("Successfully issued coupon");
    }
}
