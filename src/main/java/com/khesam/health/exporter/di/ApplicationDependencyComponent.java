package com.khesam.health.exporter.di;

import com.khesam.health.exporter.config.ConfigReader;
import com.khesam.health.exporter.scheduler.VitalSignCollectorScheduler;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {
        ExecutorModule.class,
        ObjectMapperModule.class,
        PrometheusMetricModule.class,
        VitalSignCollectorCallbackModule.class,
        VitalSignCollectorModule.class
})
public interface ApplicationDependencyComponent {

    ConfigReader configReader();

    VitalSignCollectorScheduler vitalSignCollectorScheduler();
}
