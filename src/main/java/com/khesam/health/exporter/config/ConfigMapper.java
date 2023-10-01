package com.khesam.health.exporter.config;

import org.tinylog.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Singleton
public class ConfigMapper {

    private static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;

    @Inject
    public ConfigMapper() {
    }

    ServerConfig fromServerConfigModel(
            ExporterConfigModel.ServerConfigModel serverConfigModel
    ) {
        return new ServerConfig(
                serverConfigModel.contextRoot(), serverConfigModel.port()
        );
    }

    ScheduleConfig fromScanIntervalConfigModel(
            ExporterConfigModel.ScanIntervalConfigModel scanIntervalConfigModel
    ) {
        return new ScheduleConfig(
                scanIntervalConfigModel.period(),
                getTimeUnit(scanIntervalConfigModel.unit())
        );
    }

    ServiceScan fromServiceConfigModel(
            List<ExporterConfigModel.ServiceConfigModel> serviceConfigModels
    ) {
        return new ServiceScan(
                serviceConfigModels.stream().map(e -> new ServiceScan.RemoteService(
                        e.name(), e.path(), e.caFilePath()
                )).collect(Collectors.toList())
        );
    }

    private TimeUnit getTimeUnit(String unit) {
        try {
            return TimeUnit.valueOf(
                    unit.toUpperCase(Locale.ROOT)
            );
        } catch (Exception ex) {
            Logger.error(ex, "Cannot convert time unit. So we decide to use seconds as default time unit");
            return DEFAULT_TIME_UNIT;
        }
    }
}
