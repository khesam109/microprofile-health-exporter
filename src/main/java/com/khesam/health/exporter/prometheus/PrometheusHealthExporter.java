package com.khesam.health.exporter.prometheus;

import com.khesam.health.exporter.collector.HealthStatus;
import com.khesam.health.exporter.collector.VitalSign;
import io.prometheus.client.Gauge;

public class PrometheusHealthExporter {

    private static final String HEALTH_CHECK_METRIC_NAME = "service_status";
    private static final String HEALTH_CHECK_METRIC_HELP = "Check services' vital sign";
    private static final String SERVICE_OVERALL_KEY_NAME = "";
    private static final String[] HEALTH_CHECK_LABELS = new String[] {
            "service_name", "organ_name"
    };

    private final Gauge serviceStatus;

    private static PrometheusHealthExporter INSTANCE = null;

    private PrometheusHealthExporter() {
        this.serviceStatus = Gauge.build()
                .name(HEALTH_CHECK_METRIC_NAME)
                .labelNames(HEALTH_CHECK_LABELS)
                .help(HEALTH_CHECK_METRIC_HELP)
                .register();
    }

    public static PrometheusHealthExporter getInstance() {
        if (INSTANCE == null)
            INSTANCE = new PrometheusHealthExporter();
        return INSTANCE;
    }

    public void collectData(VitalSign vitalSign) {
        measureStatus(vitalSign.getServiceName(), SERVICE_OVERALL_KEY_NAME, vitalSign.getHealthStatus());
        vitalSign.getOrgansStatus().forEach(
                (key, value) -> measureStatus(vitalSign.getServiceName(), key, value)
        );

    }

    private void measureStatus(String serviceName, String organName, HealthStatus status) {
        this.serviceStatus.labels(
                serviceName,
                organName
        ).set(
                status.code
        );
    }
}
