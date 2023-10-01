package com.khesam.health.exporter.helper;

import com.khesam.health.exporter.exception.BullshitAnswerException;
import com.khesam.health.exporter.exception.MakeAppointmentException;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import org.tinylog.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

public class ExporterHttpClient {

    private static final Duration CONNECTION_TIMEOUT = Duration.of(10, SECONDS);
    private static final Duration READ_TIMEOUT = Duration.of(10, SECONDS);

    private final SslHelper sslHelper;
    private final HttpClient httpClient;
    private final HttpRequest httpRequest;

    @AssistedInject
    public ExporterHttpClient(
            SslHelper sslHelper,
            @Assisted("path") String path,
            @Assisted("caFilePath") String caFilePath
    ) {
        this.sslHelper = sslHelper;
        if (caFilePath == null || caFilePath.isEmpty()) {
            this.httpClient = buildHttpClient();
        } else {
            this.httpClient = buildHttpClient(caFilePath);
        }
        this.httpRequest = getRequest(path);
    }

    String getVitalSign() {
        Logger.info("Start probing vital sign of: {}", httpRequest.uri().toString());
        try {
            HttpResponse<String> response = httpClient.send(
                    httpRequest,
                    HttpResponse.BodyHandlers.ofString()
            );
            if (isHappy(response.statusCode())) {
                return response.body();
            } else {
                throw new BullshitAnswerException("WTF response! Waiting for better answer. Http Status Code: " +
                        response.statusCode() + " - Http response: " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            throw new MakeAppointmentException("Failed to reach: " + this.httpRequest.uri().toString(), e);
        }
    }

    private HttpClient buildHttpClient() {
        return HttpClient.newBuilder()
                .connectTimeout(CONNECTION_TIMEOUT)
                .build();
    }

    private HttpClient buildHttpClient(String caFilePath) {
        return HttpClient.newBuilder()
                .sslContext(
                        sslHelper.buildSslContext(caFilePath)
                ).connectTimeout(CONNECTION_TIMEOUT)
                .build();
    }

    private HttpRequest getRequest(String path) {
        try {
            return HttpRequest.newBuilder()
                    .uri(new URI(path))
                    .timeout(READ_TIMEOUT)
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid format of url!");
        }

    }

    private boolean isHappy(int httpStatusCode) {
        return httpStatusCode >= 200 && httpStatusCode < 300;
    }
}
