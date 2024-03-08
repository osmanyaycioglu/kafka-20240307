package org.training.kafka.kafka.spring;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TopicKafkaListener {

    @KafkaListener(topics = "first-topic")
    public void method(String stringParam) {
        System.out.println("Gelen mesaj : " + stringParam);
    }

}
