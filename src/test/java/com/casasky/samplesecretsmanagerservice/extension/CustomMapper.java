package com.casasky.samplesecretsmanagerservice.extension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class CustomMapper {
    private CustomMapper() {
    }

    public static <T> String toJson(T object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("unable to parse object to json");
        }
    }
}
