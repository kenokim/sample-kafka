package com.example;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class SampleProducer {
    private final SampleEventRepository sampleEventRepository;
    private final KafkaTemplate<String, String> template;
    private final String topicName = "kafka-sample-topic";

    @Transactional
    @Scheduled(cron = "*/10 * * * * ?")
    public void processEvents() {
        System.out.println("Starting cron job ...");
        List<SampleEvent> events = sampleEventRepository.findAllByIsPublishedFalse();
        events.forEach(this::produceEvent);
    }

    @Transactional
    public void produceEvent(SampleEvent event) {
        System.out.println("Producing " + event.getSampleId() + " ...");
        ProducerRecord record = new ProducerRecord(topicName, event.getSampleId(), event.getSampleId());
        CompletableFuture send = template.send(record);
        try {
            send.get();
            event.setPublished(true);
            event.setPublishedAt(LocalDateTime.now());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
