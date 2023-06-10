package com.casasky.samplesecretsmanagerservice.extension;

import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.ResourceExistsException;

import java.util.Map;

import static com.casasky.samplesecretsmanagerservice.extension.AvailableContainers.LOCALSTACK;

public final class SecretManagerClientExtension {
    private SecretManagerClientExtension() {
    }

    // it would be nice if this going to be fixed in the future
    public static void fixEndpoint() {
        System.setProperty("spring.cloud.aws.endpoint", LOCALSTACK.getEndpoint().toString());
    }

    public static void idempotentCreateSecret(Map<String, String> secrets) {
        try (var secretsManagerClient = SecretsManagerClient.builder().endpointOverride(LOCALSTACK.getEndpoint()).build()) {
            secrets.forEach((id, value) -> secretsManagerClient.createSecret(builder -> builder.name(id).secretString(value)));
        } catch (ResourceExistsException ignored) {}
    }
}
