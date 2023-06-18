package com.khesam.health.exporter.helper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

public class HttpClientHelper {

    private static final Duration CONNECTION_TIMEOUT = Duration.of(10, SECONDS);
    private static final Duration READ_TIMEOUT = Duration.of(10, SECONDS);
    private static HttpClientHelper INSTANCE;

    private HttpClientHelper() {}

    public static HttpClientHelper getInstance() {
        if (INSTANCE == null)
            INSTANCE = new HttpClientHelper();
        return INSTANCE;
    }

    public HttpResponse<String> get(String path) {
        try {
            return client().send(
                    getRequest(path),
                    HttpResponse.BodyHandlers.ofString()
            );
        } catch (IOException | InterruptedException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpClient client() {
        return HttpClient.newBuilder()
                .connectTimeout(CONNECTION_TIMEOUT)
                .build();
    }

    private HttpRequest getRequest(String path) throws URISyntaxException {
        return HttpRequest.newBuilder()
                .uri(new URI(path))
                .timeout(READ_TIMEOUT)
                .GET()
                .build();
    }
}
