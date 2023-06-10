package com.casasky.samplesecretsmanagerservice;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
public class SampleData {

    @Id
    private final UUID id = UUID.randomUUID();
    private final ZonedDateTime creationTime = ZonedDateTime.now();
    private String payload;

    protected SampleData() {
    }

    public SampleData(String payload) {
        this.payload = payload;
    }

}
