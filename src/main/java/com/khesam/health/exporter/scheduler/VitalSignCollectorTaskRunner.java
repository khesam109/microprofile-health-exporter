package com.khesam.health.exporter.scheduler;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class VitalSignCollectorTaskRunner extends PeriodicTaskRunner {

    @AssistedInject
    public VitalSignCollectorTaskRunner(
            @Assisted int period,
            @Assisted TimeUnit timeUnit,
            @Assisted VitalSignCollectorTask vitalSignCollectorTask,
            ScheduledExecutorService executor
    ) {
        super(period, timeUnit, vitalSignCollectorTask, executor);
    }
}
