package com.example.KafkaStreams.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.processor.WallclockTimestampExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.Map;

import static org.apache.kafka.streams.StreamsConfig.APPLICATION_ID_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG;

@Configuration
@EnableKafka
@EnableKafkaStreams
public class KafkaStreamsConfig {

    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public KafkaStreamsConfiguration kStreamsConfigs() {
        return new KafkaStreamsConfiguration(Map.of(
                APPLICATION_ID_CONFIG, "kafkaStreams",
                BOOTSTRAP_SERVERS_CONFIG, "localhost:19092",
                DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.Integer().getClass().getName(),
                DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName(),
                DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, WallclockTimestampExtractor.class.getName()
        ));
    }

    @Bean
    NewTopic task1_1() {
        return TopicBuilder.name("task1-1")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    NewTopic task1_2() {
        return TopicBuilder.name("task1-2")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    NewTopic task2_create() {
        return TopicBuilder.name("task2")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    NewTopic task3_1() {
        return TopicBuilder.name("task3-1")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    NewTopic task3_2() {
        return TopicBuilder.name("task3-2")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    NewTopic task4_create() {
        return TopicBuilder.name("task4")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
