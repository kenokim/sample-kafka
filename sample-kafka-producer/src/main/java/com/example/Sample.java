package com.example;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Sample {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sampleId;
    private String content;

    public static Sample create(String sampleId, String content) {
        Sample sample = new Sample();
        sample.sampleId = sampleId;
        sample.content = content;
        return sample;
    }
}
