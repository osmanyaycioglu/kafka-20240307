package org.training.kafka.kafka.spring;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TopicKafkaListener {

//    @KafkaListener(topics = "first-topic",groupId = "kafka-group-1",id = "gg-1",concurrency = "3")
//    public void method(String stringParam) {
//        System.out.println("First Gelen mesaj : " + stringParam);
//    }
//
//    @KafkaListener(topics = "second-topic",groupId = "kafka-group-2",id="gg-2")
//    public void method2(String stringParam) {
//        System.out.println("Second Gelen mesaj : " + stringParam);
//    }


    @KafkaListener(topics = "another-topic",groupId = "kafka-group-1",id = "gg-1")
    public void method(String stringParam) {
        System.out.println("Another Gelen mesaj : " + stringParam);
    }

}
