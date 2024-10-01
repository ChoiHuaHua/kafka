package com.fastcampus.kafkahandson.ugc.controller;

import com.fastcampus.kafkahandson.ugc.CouponIssueHistoryUsecase;
import com.fastcampus.kafkahandson.ugc.ListUsableCouponsUsecase;
import com.fastcampus.kafkahandson.ugc.RequestCouponIssueUsecase;
import com.fastcampus.kafkahandson.ugc.coupon.ResolvedCoupon;
import com.fastcampus.kafkahandson.ugc.model.CouponDto;
import com.fastcampus.kafkahandson.ugc.model.CouponIssueRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/coupons")
public class CouponController {

    private final CouponIssueHistoryUsecase couponIssueHistoryUsecase;
    private final RequestCouponIssueUsecase requestCouponIssueUsecase;
    private final ListUsableCouponsUsecase listUsableCouponsUsecase;

    @PostMapping
    ResponseEntity<String> issue(@RequestBody CouponIssueRequest request) {
        if (!couponIssueHistoryUsecase.isFirstRequestFromUser(request.getCouponEventId(), request.getUserId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("already used coupon\n");
        }

        if (!couponIssueHistoryUsecase.hasRemainingCoupon(request.getCouponEventId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Not enough available coupons\n");
        }

        requestCouponIssueUsecase.queue(request.getUserId(), request.getCouponEventId());
        return ResponseEntity.ok("Successfully issued coupon\n");
    }

    @GetMapping
    ResponseEntity<List<CouponDto>> listUsableCoupons(@RequestParam(name = "userId" ) Long userId) {
        List<ResolvedCoupon> resolvedCoupons = listUsableCouponsUsecase.listByUserId(userId);
        return ResponseEntity.ok().body(resolvedCoupons.stream().map(this::toDto).toList());
    }

    private CouponDto toDto(ResolvedCoupon resolvedCoupon) {
        return new CouponDto(
                resolvedCoupon.getCoupon().getId(),
                resolvedCoupon.getCouponEvent().getDisplayName(),
                resolvedCoupon.getCouponEvent().getExpiredAt()
        );
    }
}
