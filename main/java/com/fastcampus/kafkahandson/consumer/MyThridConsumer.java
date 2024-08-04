package com.fastcampus.kafkahandson.consumer;

import com.fastcampus.kafkahandson.model.MyMessage;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.fastcampus.kafkahandson.model.Topic.MY_JSON_TOPIC;

@Component
public class MyThridConsumer {

    @KafkaListener(topics = MY_JSON_TOPIC, groupId = "batch-test-consumer-group", containerFactory = "batchKafkaListenerContainerFactory")
    public void accept(List<ConsumerRecord<String, MyMessage>> message) {
        System.out.println("MyThirdConsumer accept message size: " + message.size());
        message.forEach(record -> System.out.println("MyThirdConsumer message: " + record.value()));
    }
}
