package com.casasky.samplesecretsmanagerservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.awscore.client.builder.AwsDefaultClientBuilder;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;

import java.net.URI;

//TODO event this is not helping - Tests works without custom bean. Not able to use secrets manager locally
// app fails to start even with setting custom environment variables like in test
//@Configuration
public class SecretsManagerConfig {

/*    @Bean
    SecretsManagerClient secretsManagerClient() {
        return SecretsManagerClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create("foo", "foo")))
                .endpointOverride(URI.create("http://127.0.0.1:4566"))
                .region(Region.US_EAST_1)
                .build();
    }*/

}
