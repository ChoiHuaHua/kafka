package com.fastcampus.kafkahandson.consumer;

import com.fastcampus.kafkahandson.model.MyMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.fastcampus.kafkahandson.model.Topic.MY_JSON_TOPIC;

@Component
public class MyConsumer {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Map<String, Integer> idHistoryMap = new ConcurrentHashMap<>();

    @KafkaListener(topics = MY_JSON_TOPIC, groupId = "test-consumer-group")
    public void accept(ConsumerRecord<String, String> message, Acknowledgment acknowledgment) throws JsonProcessingException {
        MyMessage myMessage = objectMapper.readValue(message.value(), MyMessage.class);
        this.printPayloadIfFirstMessage(myMessage);
        acknowledgment.acknowledge(); // 수동커밋
    }

    private synchronized void printPayloadIfFirstMessage(MyMessage myMessage){
        if (idHistoryMap.putIfAbsent(String.valueOf(myMessage.getId()), 1) == null) {
            idHistoryMap.put(String.valueOf(myMessage.getId()), 1);
            System.out.println("[MyConsumer] message arrived: " + myMessage);
        } else {
            System.out.println("[MyConsumer] message duplicated: " + myMessage.getId());
        }
    }
}
