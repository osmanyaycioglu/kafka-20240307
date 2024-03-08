package org.training.kafka.kafka.spring;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class SendMessageController {
    private final KafkaTemplate<Integer, String> stringKafkaTemplate;

    @PostMapping("/send/first")
    public void send(@RequestBody String message) {
        for (int i = 0; i < 10_000; i++) {
            stringKafkaTemplate.send("first-topic",
                                     message + " " + i);
        }
        System.out.println("Sent complete");
    }

    @PostMapping("/send/second")
    public void sendSecond(@RequestBody String message) {
        stringKafkaTemplate.send("second-topic",
                                 message);
    }

}
