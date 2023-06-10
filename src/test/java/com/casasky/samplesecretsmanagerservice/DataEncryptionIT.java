package com.casasky.samplesecretsmanagerservice;

import com.casasky.samplesecretsmanagerservice.extension.CustomMapper;
import com.casasky.samplesecretsmanagerservice.extension.EnableEncryptionSecret;
import com.casasky.samplesecretsmanagerservice.extension.SecretManagerClientExtension;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Map;

import static com.casasky.samplesecretsmanagerservice.extension.AvailableContainers.LOCALSTACK;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests only the data encryption logic without persistence context
 */
@EnableEncryptionSecret
@EnableAutoConfiguration(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class
})
class DataEncryptionIT {

    static final String PROTOTYPE_ENCRYPTION_KEY = "prototype-encryption-key";

    // this bean should be mocked because it depends on persistence context
    // TODO is there another way to do that?
    @MockBean
    SampleDataService sampleDataService;

    @Autowired
    DataEncryption sut;


    static {
        LOCALSTACK.start();
        SecretManagerClientExtension.fixEndpoint();

        var encryptionSecrets = CustomMapper.toJson(Map.of("encryption_key", PROTOTYPE_ENCRYPTION_KEY));

        SecretManagerClientExtension.idempotentCreateSecret(Map.of("/test/casasky/encryption", encryptionSecrets));
    }


    @Test
    void shouldFindEncryptionKey() {
        assertThat(sut.encrypt()).isEqualTo(PROTOTYPE_ENCRYPTION_KEY);
    }

}