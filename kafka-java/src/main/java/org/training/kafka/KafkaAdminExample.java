package org.training.kafka;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaAdminExample {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,
                       "127.0.0.1:9092");
        try (Admin admin = Admin.create(properties)) {

            DescribeTopicsResult describeTopicsResultLoc = admin.describeTopics(Arrays.asList("first-topic"));
            Map<String, KafkaFuture<TopicDescription>> stringKafkaFutureMapLoc = describeTopicsResultLoc.topicNameValues();
            KafkaFuture<TopicDescription> topicDescriptionKafkaFutureLoc = stringKafkaFutureMapLoc.get("first-topic");
            TopicDescription topicDescriptionLoc = null;
            try {
                topicDescriptionLoc = topicDescriptionKafkaFutureLoc.get();
                System.out.println(topicDescriptionLoc);
            } catch (Exception eParam) {
                eParam.printStackTrace();
            }
//            int   partitions        = 10;
//            short replicationFactor = 3;
//            NewTopic newTopic = new NewTopic("my-first-topic",
//                                             partitions,
//                                             replicationFactor);
//            CreateTopicsResult result = admin.createTopics(Collections.singleton(newTopic));
//            KafkaFuture<Void> future = result.values()
//                                             .get("my-first-topic");
//            try {
//                future.get();
//            } catch (Exception eParam) {
//                eParam.printStackTrace();
//            }
        }
    }
}
