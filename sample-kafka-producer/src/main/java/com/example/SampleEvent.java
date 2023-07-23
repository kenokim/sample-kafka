package com.example;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
public class SampleEvent {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sampleId;
    @Setter
    private boolean isPublished;
    @Setter
    private LocalDateTime publishedAt;

    public static SampleEvent create(Sample sample) {
        SampleEvent event = new SampleEvent();
        event.sampleId = sample.getSampleId();
        return event;
    }
}
