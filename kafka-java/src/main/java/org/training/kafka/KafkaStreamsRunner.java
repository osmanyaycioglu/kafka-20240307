package org.training.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;

import java.util.Properties;

public class KafkaStreamsRunner {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG,
                  "app_deneme");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,
                  "127.0.0.1:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG,
                  Serdes.Integer()
                        .getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,
                  Serdes.String()
                        .getClass());
        props.put(StreamsConfig.CLIENT_ID_CONFIG,
                  "client1");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
                  "earliest");

        StreamsBuilder           streamsBuilder = new StreamsBuilder();
        KStream<Integer, String> kStream        = streamsBuilder.stream("first-topic");
        kStream.foreach((k, v) -> System.out.println("Key= " + k + " Value= " + v));
        //kStream.peek((k,v)-> System.out.println("Key= " + k + " Value= " + v));

        Topology     topology = streamsBuilder.build();
        KafkaStreams streams  = new KafkaStreams(topology,
                                                 props);
        System.out.println("Starting stream.");
        streams.start();

        Runtime.getRuntime()
               .addShutdownHook(new Thread(() -> {
                   System.out.println("Shutting down stream");
                   streams.close();
               }));
    }
}
