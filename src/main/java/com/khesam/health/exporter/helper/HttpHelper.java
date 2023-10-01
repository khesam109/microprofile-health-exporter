package com.khesam.health.exporter.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.khesam.health.exporter.collector.model.HealthStatus;
import com.khesam.health.exporter.collector.model.VitalSign;
import com.khesam.health.exporter.config.ServiceScan;
import com.khesam.health.exporter.di.HttpClientFactory;
import org.tinylog.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class HttpHelper {

    private final HttpClientFactory httpClientFactory;
    private final VitalSignMapper vitalSignMapper;
    private final ObjectMapper objectMapper;
    private Map<String, ExporterHttpClient> exporterHttpClients;

    @Inject
    public HttpHelper(
            HttpClientFactory httpClientFactory,
            VitalSignMapper vitalSignMapper,
            ObjectMapper objectMapper
    ) {
        this.httpClientFactory = httpClientFactory;
        this.vitalSignMapper = vitalSignMapper;
        this.objectMapper = objectMapper;
    }

    public List<VitalSign> probeVitalSign(ServiceScan serviceScan) {
        if (exporterHttpClients == null) {
            init(serviceScan);
        }

        List<VitalSign> vitalSigns = new ArrayList<>();
        this.exporterHttpClients.forEach((name, httpClient) -> {
            try {
                String healthResponse = httpClient.getVitalSign();
                if (healthResponse != null && !healthResponse.isEmpty()) {
                    vitalSigns.add(
                            vitalSignMapper.fromServiceNameAndMicroprofileHealthCheckResponseData(
                                    name, objectMapper.readValue(
                                            httpClient.getVitalSign(), MicroprofileHealthCheckResponseData.class
                                    )
                            )
                    );
                } else {
                    vitalSigns.add(
                            new VitalSign(name, HealthStatus.UNKWON)
                    );
                }
            } catch (JsonProcessingException e) {
                vitalSigns.add(new VitalSign(name, HealthStatus.UNKWON));
            }
        });

        return vitalSigns;
    }

    private void init(ServiceScan serviceScan) {
        if (exporterHttpClients != null) {
            Logger.info("Http Clients is already init!");
        } else {
            this.exporterHttpClients = new HashMap<>();
            serviceScan.services().forEach(
                    e -> exporterHttpClients.put(
                            e.name(),
                            httpClientFactory.buildExporterHttpClient(e.url(), e.caFilePath())
                    )
            );
        }
    }
}
