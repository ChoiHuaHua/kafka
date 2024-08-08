package com.fastcampus.kafkahandson.consumer;

import com.fastcampus.kafkahandson.model.MyMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.fastcampus.kafkahandson.model.Topic.MY_JSON_TOPIC;

@Component
public class MyBatchConsumer {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = MY_JSON_TOPIC, groupId = "batch-test-consumer-group", containerFactory = "batchKafkaListenerContainerFactory", concurrency = "3")
    public void listen(List<ConsumerRecord<String, String>> messages, Acknowledgment acknowledgment) {

        messages.forEach(message -> {
            MyMessage myMessage;
            try {
                myMessage = objectMapper.readValue(message.value(), MyMessage.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("MyThirdConsumer(" + Thread.currentThread().getId() + ") message: " + myMessage + " partition:" + message.partition());
                }
        );
        acknowledgment.acknowledge();
    }
}
