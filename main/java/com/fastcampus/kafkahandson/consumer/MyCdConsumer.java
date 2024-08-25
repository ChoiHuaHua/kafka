package com.fastcampus.kafkahandson.consumer;

import com.fastcampus.kafkahandson.common.CustomObjectMapper;
import com.fastcampus.kafkahandson.model.MyCdcMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.fastcampus.kafkahandson.model.Topic.MY_CDC_TOPIC;

@Component
public class MyCdConsumer {

    private final CustomObjectMapper objectMapper = new CustomObjectMapper();
    private int retryCount = 0;

    @KafkaListener(topics = MY_CDC_TOPIC, groupId = "cdc-consumer-group", concurrency = "3")
    public void listen(ConsumerRecord<String, String> message, Acknowledgment acknowledgment) throws JsonProcessingException, IllegalAccessException {
        String retryPrint = retryCount != 0 ? "Retry Count: " + retryCount : "";

        MyCdcMessage myCdcMessage = objectMapper.readValue(message.value(), MyCdcMessage.class);
        System.out.println( "[Cdc Consumer] Message arrived!" + myCdcMessage.getPayload() + " " + retryPrint+ " " + LocalDateTime.now());
        retryCount++;

        throw new IllegalArgumentException("Test Exception");
        //acknowledgment.acknowledge(); // 수동커밋
    }
}
