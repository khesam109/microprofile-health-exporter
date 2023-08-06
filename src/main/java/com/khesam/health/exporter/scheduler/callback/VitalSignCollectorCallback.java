package com.khesam.health.exporter.scheduler.callback;

import com.khesam.health.exporter.collector.model.VitalSign;
import org.tinylog.Logger;

import java.util.List;

public interface VitalSignCollectorCallback extends PeriodicTaskRunnerCallback {

    void onSuccess(List<VitalSign> vitalSigns);
    default void onFiled(String message) {
        Logger.error(message);
    }
}
