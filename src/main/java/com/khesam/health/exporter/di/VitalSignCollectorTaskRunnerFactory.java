package com.khesam.health.exporter.di;

import com.khesam.health.exporter.scheduler.VitalSignCollectorTask;
import com.khesam.health.exporter.scheduler.VitalSignCollectorTaskRunner;
import dagger.assisted.AssistedFactory;

import java.util.concurrent.TimeUnit;

@AssistedFactory
public interface VitalSignCollectorTaskRunnerFactory {

    VitalSignCollectorTaskRunner buildVitalSignCollectorTaskRunner(
            int period,
            TimeUnit timeUnit,
            VitalSignCollectorTask vitalSignCollectorTask
    );
}
