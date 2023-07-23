package com.example;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@Transactional
@RestController
@RequestMapping("/samples")
@RequiredArgsConstructor
public class SampleController {
    private final SampleRepository sampleRepository;
    private final SampleEventRepository sampleEventRepository;

    @GetMapping
    public String createRandomSample() {
        String sampleId = UUID.randomUUID().toString();
        String content = "Hello content " + LocalDateTime.now();

        Sample sample = Sample.create(sampleId, content);
        sampleRepository.save(sample);

        SampleEvent event = SampleEvent.create(sample);
        sampleEventRepository.save(event);

        return sampleId;
    }

    @GetMapping("{sampleId}")
    public Sample getSample(@PathVariable String sampleId) {
        return sampleRepository.findBySampleId(sampleId).orElseThrow();
    }

    @PostConstruct
    public void createSamples() {
        for (int i = 0; i < 10; i++)
            this.createRandomSample();
    }
}
