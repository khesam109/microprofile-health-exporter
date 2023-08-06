package com.khesam.health.exporter.di;

import com.khesam.health.exporter.helper.ExporterHttpClient;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedFactory;

@AssistedFactory
public interface HttpClientFactory {

    ExporterHttpClient buildExporterHttpClient(
            @Assisted("path")String path,
            @Assisted("caFilePath") String caFilePath
    );
}
