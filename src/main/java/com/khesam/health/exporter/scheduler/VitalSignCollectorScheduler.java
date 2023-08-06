package com.khesam.health.exporter.scheduler;

import com.khesam.health.exporter.config.ScheduleConfig;
import com.khesam.health.exporter.config.ServiceScan;
import com.khesam.health.exporter.di.VitalSignCollectorTaskFactory;
import com.khesam.health.exporter.di.VitalSignCollectorTaskRunnerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class VitalSignCollectorScheduler {

    private final VitalSignCollectorTaskRunnerFactory vitalSignCollectorTaskRunnerFactory;
    private final VitalSignCollectorTaskFactory vitalSignCollectorTaskFactory;

    @Inject
    public VitalSignCollectorScheduler(
            VitalSignCollectorTaskRunnerFactory vitalSignCollectorTaskRunnerFactory,
            VitalSignCollectorTaskFactory vitalSignCollectorTaskFactory
    ) {
        this.vitalSignCollectorTaskRunnerFactory = vitalSignCollectorTaskRunnerFactory;
        this.vitalSignCollectorTaskFactory = vitalSignCollectorTaskFactory;
    }

    public VitalSignCollectorTaskRunner getVitalSignCollectorTaskRunner(
            ScheduleConfig scheduleConfig, ServiceScan serviceScan
    ) {
        return vitalSignCollectorTaskRunnerFactory.buildVitalSignCollectorTaskRunner(
                scheduleConfig.period(), scheduleConfig.unit(),
                vitalSignCollectorTaskFactory.buildVitalSignCollectorTask(serviceScan)
        );
    }
}
