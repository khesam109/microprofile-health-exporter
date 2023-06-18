package com.khesam.health.exporter.scheduler;

import com.khesam.health.exporter.collector.VitalSign;

import java.util.Collection;

public interface VitalSignCollectorCallback extends PeriodicTaskRunnerCallback {

    void onSuccess(Collection<VitalSign> vitalSigns);
}
