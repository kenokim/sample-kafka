package com.example;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class SimpleConsumer {
    public static void main(String[] args) {

        String topicName = "simple-name";

        Properties props = new Properties();
        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class.getName());
        props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "group_01");

        KafkaConsumer<String, Object> kafkaConsumer = new KafkaConsumer<String, Object>(props);
        kafkaConsumer.subscribe(List.of(topicName));

        while (true) {
            ConsumerRecords<String, Object> poll = kafkaConsumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord record : poll) {
                // record.key(); record.value();
                // poll 로 가져온 데이터로 상대적으로 시간이 걸리는 작업을 수행하는 메인 Thread
            }
        }

    }
}
