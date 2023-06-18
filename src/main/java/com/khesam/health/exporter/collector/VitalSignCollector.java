package com.khesam.health.exporter.collector;

import com.khesam.health.exporter.config.ApplicationParameter;
import com.khesam.health.exporter.helper.HealthCheckResponseData;
import com.khesam.health.exporter.helper.HttpClientHelper;
import com.khesam.health.exporter.helper.JsonHelper;
import com.khesam.health.exporter.scheduler.VitalSignCollectorCallback;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.Set;

public class VitalSignCollector implements Runnable {

    private final HttpClientHelper httpClientHelper;
    private final JsonHelper jsonHelper;
    private final VitalSignMapper mapper;
    private final VitalSignCollectorCallback callback;

    public VitalSignCollector(
            HttpClientHelper httpClientHelper,
            JsonHelper jsonHelper,
            VitalSignMapper mapper,
            VitalSignCollectorCallback callback
    ) {
        this.httpClientHelper = httpClientHelper;
        this.jsonHelper = jsonHelper;
        this.mapper = mapper;
        this.callback = callback;
    }

    @Override
    public void run() {
        Set<VitalSign> responses = new HashSet<>();
        ApplicationParameter.services().forEach(
                service -> {
                    try {
                        HttpResponse<String> response = httpClientHelper.get(service.path());
                        responses.add(
                                mapper.fromHealthCheckResponseData(
                                        service.name(),
                                        jsonHelper.getJsonMapper().readValue(
                                                response.body(),
                                                HealthCheckResponseData.class)
                                )
                        );
                    } catch (Exception e) {
                        responses.add(new VitalSign(service.name(), HealthStatus.UNKWON));
                    }
                }
        );

        callback.onSuccess(responses);
    }
}
