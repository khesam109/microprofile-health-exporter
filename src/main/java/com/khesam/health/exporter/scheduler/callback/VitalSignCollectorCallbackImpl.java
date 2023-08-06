package com.khesam.health.exporter.scheduler.callback;

import com.khesam.health.exporter.collector.model.VitalSign;
import com.khesam.health.exporter.prometheus.PrometheusVitalSignExporter;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class VitalSignCollectorCallbackImpl implements VitalSignCollectorCallback {

    private final PrometheusVitalSignExporter prometheusVitalSignExporter;

    @Inject
    public VitalSignCollectorCallbackImpl(
            PrometheusVitalSignExporter prometheusVitalSignExporter
    ) {
        this.prometheusVitalSignExporter = prometheusVitalSignExporter;
    }

    @Override
    public void onSuccess(List<VitalSign> vitalSigns) {
        vitalSigns.forEach(prometheusVitalSignExporter::collectData);
    }
}
