package com.khesam.health.exporter.helper;

import com.khesam.health.exporter.collector.model.HealthStatus;
import com.khesam.health.exporter.collector.model.VitalSign;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class VitalSignMapper {

    @Inject
    public VitalSignMapper() {
    }

    VitalSign fromServiceNameAndMicroprofileHealthCheckResponseData(
            String serviceName,
            MicroprofileHealthCheckResponseData responseData
    ) {
        VitalSign vitalSign = new VitalSign(
                serviceName, HealthStatus.fromLiteral(responseData.status())
        );

        if (responseData.checks() != null && !responseData.checks().isEmpty()) {
            responseData.checks().forEach(e ->
                    vitalSign.addOrganStatus(
                            e.name(), HealthStatus.fromLiteral(e.status())
                    )
            );
        }
        return vitalSign;
    }
}
