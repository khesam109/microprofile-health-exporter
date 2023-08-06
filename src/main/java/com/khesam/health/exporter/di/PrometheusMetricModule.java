package com.khesam.health.exporter.di;

import dagger.Module;
import dagger.Provides;
import io.prometheus.client.Gauge;

import javax.inject.Singleton;

@Module
public class PrometheusMetricModule {

    private static final String VITAL_SIGN_METRIC_NAME = "service_status";
    private static final String VITAL_SIGN_METRIC_HELP = "Check services' vital sign";
    private static final String[] VITAL_SIGN_LABELS = new String[] {
            "service_name", "organ_name"
    };

    @Provides
    @Singleton
    public Gauge provideGauge() {
        return Gauge.build()
                .name(VITAL_SIGN_METRIC_NAME)
                .labelNames(VITAL_SIGN_LABELS)
                .help(VITAL_SIGN_METRIC_HELP)
                .register();
    }
}
