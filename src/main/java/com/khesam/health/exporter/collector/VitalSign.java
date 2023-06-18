package com.khesam.health.exporter.collector;

import java.util.HashMap;
import java.util.Map;

public class VitalSign {

    private final String serviceName;
    private final HealthStatus healthStatus;
    private final Map<String, HealthStatus> organsStatus;

    public VitalSign(String serviceName, HealthStatus healthStatus) {
        this.serviceName = serviceName;
        this.healthStatus = healthStatus;
        this.organsStatus = new HashMap<>();
    }

    public void addOrganStatus(String organName, HealthStatus healthStatus) {
        this.organsStatus.put(organName, healthStatus);
    }

    public String getServiceName() {
        return serviceName;
    }

    public HealthStatus getHealthStatus() {
        return healthStatus;
    }

    public Map<String, HealthStatus> getOrgansStatus() {
        return organsStatus;
    }
}
