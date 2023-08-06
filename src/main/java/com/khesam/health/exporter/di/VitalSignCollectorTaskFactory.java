package com.khesam.health.exporter.di;

import com.khesam.health.exporter.config.ServiceScan;
import com.khesam.health.exporter.scheduler.VitalSignCollectorTask;
import dagger.assisted.AssistedFactory;

@AssistedFactory
public interface VitalSignCollectorTaskFactory {

    VitalSignCollectorTask buildVitalSignCollectorTask(ServiceScan serviceScan);
}
