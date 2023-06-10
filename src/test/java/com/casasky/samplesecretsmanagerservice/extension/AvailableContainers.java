package com.casasky.samplesecretsmanagerservice.extension;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.utility.DockerImageName;

public final class AvailableContainers {

    public static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:14.8-alpine");

    public static final LocalStackContainer LOCALSTACK = new LocalStackContainer(DockerImageName.parse("localstack/localstack"))
            .withEnv("DEFAULT_REGION", "eu-central-1");


    private AvailableContainers() {
    }

}
