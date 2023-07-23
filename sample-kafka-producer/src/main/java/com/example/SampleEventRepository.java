package com.example;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SampleEventRepository extends JpaRepository<SampleEvent, Long> {
    List<SampleEvent> findAllByIsPublishedFalse();
}
