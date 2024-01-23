package com.example.KafkaStreams.tasks.task2;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.regex.Pattern;

@Component
public class Task2 {
    @Bean
    public Map<String, KStream<Integer, String>> processTask2(StreamsBuilder builder) {
        Pattern pattern = Pattern.compile("\\W+");
        int shortWordThreshold = 10;

        KStream<String, String> sourceStream = builder.stream("task2", Consumed.with(Serdes.String(), Serdes.String()));

        KStream<Integer, String> wordStream = sourceStream
                .filter((key, value) -> value != null)
                .flatMapValues(value -> Arrays.asList(pattern.split(value.toLowerCase())))
                .selectKey((key, value) -> value.length());

        wordStream.foreach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));

        Predicate<Integer, String> isShortWord = (key, value) -> key < shortWordThreshold;
        Predicate<Integer, String> isLongWord = (key, value) -> key >= shortWordThreshold;
        KStream<Integer, String>[] branches = wordStream.branch(isShortWord, isLongWord);

        KStream<Integer, String> shortWordsStream = branches[0].filter((key, value) -> value.contains("a"));
        KStream<Integer, String> longWordsStream = branches[1].filter((key, value) -> value.contains("a"));

        KStream<Integer, String> mergedStream = shortWordsStream.merge(longWordsStream);
        mergedStream.foreach((key, value) -> System.out.println("Merged Stream - Key: " + key + ", Value: " + value));

        return Map.of(
                "shortWordsWithA", shortWordsStream,
                "longWordsWithA", longWordsStream
        );
    }
}
