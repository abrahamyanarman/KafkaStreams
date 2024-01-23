package com.example.KafkaStreams.tasks.task4;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Task4 {
    @Bean
    public KStream<String, User> processTask4(StreamsBuilder builder) {
        KStream<String, User> userKStream = builder.stream("task4",
                Consumed.with(Serdes.String(), Serdes.serdeFrom(new JsonSerializer<>(), new JsonDeserializer<>(User.class))));

        userKStream
                .filter((key, value) -> value != null)
                .foreach((key, value) -> System.out.println("Received message: " + value));

        return userKStream;
    }
}
