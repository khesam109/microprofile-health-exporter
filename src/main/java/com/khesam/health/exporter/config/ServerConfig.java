package com.khesam.health.exporter.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ServerConfig(
        @JsonProperty("root_context") String rootContext,
        @JsonProperty("port") int port
) {
}
