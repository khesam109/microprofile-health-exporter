package com.khesam.health.exporter.config;

import java.util.List;

public record ServiceScan(
        List<RemoteService> services
) {

    public record RemoteService(
            String name,
            String url,
            String caFilePath
    ) {

    }
}
