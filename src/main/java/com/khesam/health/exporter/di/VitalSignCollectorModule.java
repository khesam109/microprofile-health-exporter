package com.khesam.health.exporter.di;

import com.khesam.health.exporter.collector.ApplicationVitalSignCollector;
import com.khesam.health.exporter.collector.VitalSignCollector;
import dagger.Binds;
import dagger.Module;

import javax.inject.Singleton;

@Module
public abstract class VitalSignCollectorModule {

    @Binds
    @Singleton
    abstract VitalSignCollector applicationVitalSignCollector(
            ApplicationVitalSignCollector applicationVitalSignCollector
    );
}
