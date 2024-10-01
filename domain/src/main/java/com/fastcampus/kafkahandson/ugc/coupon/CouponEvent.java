package com.fastcampus.kafkahandson.ugc.coupon;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CouponEvent {

    private Long id;
    private String displayName;
    private LocalDateTime expiredAt;
    private Long issueLimit;

    @JsonIgnore
    public boolean isExpired() {
        return expiredAt.isBefore(LocalDateTime.now());
    }
}
