package com.khesam.health.exporter.collector;

import com.khesam.health.exporter.helper.HealthCheckResponseData;

public final class VitalSignMapper {

    private static VitalSignMapper INSTANCE;

    private VitalSignMapper() {}

    public static VitalSignMapper getInstance() {
        if (INSTANCE == null)
            INSTANCE = new VitalSignMapper();
        return INSTANCE;
    }

    public VitalSign fromHealthCheckResponseData(String serviceName, HealthCheckResponseData responseData) {
        VitalSign vitalSign = new VitalSign(serviceName, HealthStatus.fromLiteral(responseData.status()));
        if (responseData.checks() != null && !responseData.checks().isEmpty()) {
            responseData.checks().forEach(
                    e -> vitalSign.addOrganStatus(
                            e.name(),
                            HealthStatus.fromLiteral(e.status())
                    )
            );
        }
        return vitalSign;
    }
}
