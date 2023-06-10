package com.casasky.samplesecretsmanagerservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * Tests persisting data with light context
 */
@SpringBootTest // TODO could it be replaced with other annotation?
class SampleDataServiceIT {

    // should be mocked because it depends on secrets manager and this is out of the test context
    // TODO is there another way to do that?
    @MockBean
    DataEncryption dataEncryption;

    @Autowired
    SampleDataService sut;

    @ServiceConnection
    static final PostgreSQLContainer<?> MANAGED_POSTGRES = new PostgreSQLContainer<>("postgres:14.8-alpine");

    static {
        MANAGED_POSTGRES.start();
    }

    @Test
    void simpleSampleDataSaveCheck() {
        assertDoesNotThrow(() -> sut.save(new SampleData("{\"context\": \"diverse\"}")));
    }

}