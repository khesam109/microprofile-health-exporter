package com.khesam.health.exporter.helper;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHelper {

    private final ObjectMapper OBJECT_MAPPER;

    private static JsonHelper INSTANCE;

    private JsonHelper() {
        OBJECT_MAPPER = new ObjectMapper();
    }

    public static JsonHelper getInstance() {
        if (INSTANCE == null)
            INSTANCE = new JsonHelper();
        return INSTANCE;
    }

    public ObjectMapper getJsonMapper() {
        return OBJECT_MAPPER;
    }
}
