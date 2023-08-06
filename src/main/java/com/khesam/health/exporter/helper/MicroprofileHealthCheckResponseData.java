package com.khesam.health.exporter.helper;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public record MicroprofileHealthCheckResponseData(
        @JsonProperty("status") String status,
        @JsonProperty("checks") List<Check> checks
) implements Serializable {

    public record Check(
            @JsonProperty("name") String name,
            @JsonProperty("status") String status,
            @JsonProperty("data") HashMap<String, String> data
    ) implements Serializable {
    }
}
