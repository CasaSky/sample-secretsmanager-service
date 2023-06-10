package com.casasky.samplesecretsmanagerservice;

import com.casasky.samplesecretsmanagerservice.extension.CustomMapper;
import com.casasky.samplesecretsmanagerservice.extension.EnableAllSecrets;
import com.casasky.samplesecretsmanagerservice.extension.SecretManagerClientExtension;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.testcontainers.lifecycle.Startables;

import java.util.Map;

import static com.casasky.samplesecretsmanagerservice.extension.AvailableContainers.LOCALSTACK;
import static com.casasky.samplesecretsmanagerservice.extension.AvailableContainers.POSTGRES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * Tests that everything is set up correctly on application start. This covers the secrets manager magic.
 */
@EnableAllSecrets
class ApplicationStartupIT {

    static final String PROTOTYPE_ENCRYPTION_KEY = "prototype-encryption-key";

    @Autowired
    SampleDataService sampleDataService;

    @Autowired
    DataEncryption dataEncryption;


    static {
        LOCALSTACK.withEnv("DEFAULT_REGION", "eu-central-1");
        Startables.deepStart(POSTGRES, LOCALSTACK).join();
        SecretManagerClientExtension.fixEndpoint();

        // db secrets are needed only for this special test - usually the cool @ServiceConnection should be used
        var dbSecrets = CustomMapper.toJson(Map.of("url", POSTGRES.getJdbcUrl(), "username", POSTGRES.getUsername(), "password", POSTGRES.getPassword()));
        var encryptionSecrets = CustomMapper.toJson(Map.of("encryption_key", PROTOTYPE_ENCRYPTION_KEY));

        SecretManagerClientExtension.idempotentCreateSecret(Map.of("/test/casasky/db", dbSecrets, "/test/casasky/encryption", encryptionSecrets));
    }

    @Test
    void appStartAfterProvidingDbSecrets() {
        assertDoesNotThrow(() -> sampleDataService.save(new SampleData("running app and discovering of db secrets from lib were successful")));
    }

    @Test
    void accessToEncryptionKeyFromSecretsManager() {
        assertThat(dataEncryption.encrypt()).isNotBlank();
    }

}
