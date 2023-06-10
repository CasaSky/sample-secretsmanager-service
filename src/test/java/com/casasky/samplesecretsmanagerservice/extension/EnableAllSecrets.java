package com.casasky.samplesecretsmanagerservice.extension;

import org.springframework.boot.test.context.SpringBootTest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@SpringBootTest(properties = "spring.config.import=aws-secretsmanager:/test/casasky/db;/test/casasky/encryption")
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableAllSecrets {
}
