package com.example.KafkaStreams.tasks.task1;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Task1 {
    @Bean
    public KStream<String, String> processTask1(StreamsBuilder builder) {
        KStream<String, String> stream = builder.stream("task1-1");
        stream.to("task1-2");
        return stream;
    }


}
