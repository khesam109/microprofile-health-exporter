package com.khesam.health.exporter.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Service(
        @JsonProperty("name") String name,
        @JsonProperty("path") String path
) {
}
