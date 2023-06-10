package com.casasky.samplesecretsmanagerservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class DataEncryption {

    @Value("${encryption_key}")
    private String encryptionKey;

    /**
     * Uses encryption key from aws secrets manager to encrypt data
     *  fyi: The secret itself is being returned for simple prototyping of the new secrets manager lib
     */
    public String encrypt() {
        return encryptionKey;
    }

}
