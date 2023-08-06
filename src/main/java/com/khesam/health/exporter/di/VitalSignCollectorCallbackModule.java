package com.khesam.health.exporter.di;

import com.khesam.health.exporter.scheduler.callback.VitalSignCollectorCallback;
import com.khesam.health.exporter.scheduler.callback.VitalSignCollectorCallbackImpl;
import dagger.Binds;
import dagger.Module;

import javax.inject.Singleton;

@Module
public abstract class VitalSignCollectorCallbackModule {

    @Binds
    @Singleton
    abstract VitalSignCollectorCallback vitalSignCollectorCallback(
            VitalSignCollectorCallbackImpl vitalSignCollectorCallbackImpl
    );
}
