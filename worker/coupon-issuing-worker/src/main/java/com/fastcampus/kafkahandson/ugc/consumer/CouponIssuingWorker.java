package com.fastcampus.kafkahandson.ugc.consumer;

import com.fastcampus.kafkahandson.ugc.CouponIssueUsecase;
import com.fastcampus.kafkahandson.ugc.CustomObjectMapper;
import com.fastcampus.kafkahandson.ugc.adapter.common.Topic;
import com.fastcampus.kafkahandson.ugc.adapter.couponissuerequest.CouponIssueRequestMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CouponIssuingWorker {

    private final CustomObjectMapper objectMapper = new CustomObjectMapper();

    private final CouponIssueUsecase couponIssueUsecase;

    @KafkaListener(topics = {Topic.COUPON_ISSUE_TOPIC}, groupId = "coupon-issuing-consumer-group", concurrency = "3")
    public void listen(ConsumerRecord<String, String> message) throws JsonProcessingException {

        CouponIssueRequestMessage couponIssueRequestMessage = objectMapper.readValue(message.value(), CouponIssueRequestMessage.class);
        couponIssueUsecase.save(couponIssueRequestMessage.getCouponEventId(), couponIssueRequestMessage.getUserId());
    }
}
