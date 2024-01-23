package com.example.KafkaStreams.tasks.task3;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.JoinWindows;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.StreamJoined;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.regex.Pattern;

@Component
public class Task3 {
    @Bean
    public KStream<Long, String> processTask3(StreamsBuilder builder) {
        Pattern pattern = Pattern.compile(":");

        KStream<Long, String> stream1 = builder.stream("task3-1", Consumed.with(Serdes.String(), Serdes.String()))
                .filter((key, value) -> value != null && value.contains(":"))
                .selectKey((key, value) -> Long.parseLong(pattern.split(value)[0]));

        KStream<Long, String> stream2 = builder.stream("task3-2", Consumed.with(Serdes.String(), Serdes.String()))
                .filter((key, value) -> value != null && value.contains(":"))
                .selectKey((key, value) -> Long.parseLong(pattern.split(value)[0]));

        KStream<Long, String> joinedStream = stream1.join(
                stream2,
                (value1, value2) -> value1 + " + " + value2,
                JoinWindows.of(Duration.ofMinutes(1)).grace(Duration.ofSeconds(30)),
                StreamJoined.with(Serdes.Long(), Serdes.String(), Serdes.String())
        );

        joinedStream.foreach((key, value) -> System.out.println("Joined Message - Key: " + key + ", Value: " + value));

        return joinedStream;
    }
}
