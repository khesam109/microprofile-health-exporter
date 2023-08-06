package com.khesam.health.exporter.config;

public record ServerConfig(
        String contextRoot,
        int port
) {
}
