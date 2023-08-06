package com.khesam.health.exporter.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

record ExporterConfigModel(
        @JsonProperty("server_config") ServerConfigModel serverConfigModel,
        @JsonProperty("scan_interval") ScanIntervalConfigModel scanIntervalConfigModel,
        @JsonProperty("services") List<ServiceConfigModel> serviceConfigModels
) {

    record ServerConfigModel(
            @JsonProperty("context_root") String contextRoot,
            @JsonProperty("port") int port
    ) {
    }

    record ScanIntervalConfigModel(
            @JsonProperty("period") int period,
            @JsonProperty("unit") String unit
    ) {
    }

    record ServiceConfigModel(
            @JsonProperty("name") String name,
            @JsonProperty("path") String path,
            @JsonProperty("ca_file_path") String caFilePath
    ) {

    }
}
