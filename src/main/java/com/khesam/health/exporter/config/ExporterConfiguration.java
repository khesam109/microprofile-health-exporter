package com.khesam.health.exporter.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ExporterConfiguration(
        @JsonProperty("scan_interval") ScanInterval scanInterval,
        @JsonProperty("server_config") ServerConfig serverConfig,
        @JsonProperty("services") List<Service> services
) {
}
