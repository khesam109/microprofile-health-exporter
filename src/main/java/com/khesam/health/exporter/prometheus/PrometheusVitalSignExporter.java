package com.khesam.health.exporter.prometheus;

import com.khesam.health.exporter.collector.model.HealthStatus;
import com.khesam.health.exporter.collector.model.VitalSign;
import io.prometheus.client.Gauge;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PrometheusVitalSignExporter {

    private static final String SERVICE_OVERALL_KEY_NAME = "";

    private final Gauge serviceStatus;

    @Inject
    public PrometheusVitalSignExporter(Gauge serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public void collectData(VitalSign vitalSign) {
        measureStatus(vitalSign.serviceName(), SERVICE_OVERALL_KEY_NAME, vitalSign.healthStatus());
        vitalSign.organsStatus().forEach((key, value) -> measureStatus(vitalSign.serviceName(), key, value));
    }
    private void measureStatus(String serviceName, String organName, HealthStatus healthStatus) {
        this.serviceStatus.labels(
                serviceName,
                organName
        ).set(
                healthStatus.code
        );
    }

}
