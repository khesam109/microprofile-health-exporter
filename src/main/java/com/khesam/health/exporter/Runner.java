package com.khesam.health.exporter;

import com.khesam.health.exporter.collector.VitalSignCollector;
import com.khesam.health.exporter.collector.VitalSignMapper;
import com.khesam.health.exporter.config.ApplicationParameter;
import com.khesam.health.exporter.config.ConfigReader;
import com.khesam.health.exporter.helper.HttpClientHelper;
import com.khesam.health.exporter.helper.JsonHelper;
import com.khesam.health.exporter.prometheus.PrometheusHealthExporter;
import com.khesam.health.exporter.scheduler.PeriodicTaskRunner;
import com.sun.net.httpserver.HttpServer;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.HTTPServer;
import org.tinylog.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Runner {

    public static void main(String[] args) {
        try {
            ConfigReader.init();
            runServer();
            runScheduler();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void runServer() throws IOException {
        Logger.info("Starting exporter...");
        HttpServer httpServer = HttpServer.create(
                new InetSocketAddress(
                        ApplicationParameter.serverConfig().port()
                ), 3
        );

        httpServer.createContext(
                ApplicationParameter.serverConfig().rootContext(),
                new HTTPServer.HTTPMetricHandler(CollectorRegistry.defaultRegistry)
        );

        new HTTPServer.Builder()
                .withHttpServer(httpServer)
                .build();

        Logger.info("Http endpoint successfully stated");
    }

    private static void runScheduler() {
        PeriodicTaskRunner.getInstance().run(
                new VitalSignCollector(
                        HttpClientHelper.getInstance(),
                        JsonHelper.getInstance(),
                        VitalSignMapper.getInstance(),
                        vitalSigns -> vitalSigns.forEach(
                                e -> PrometheusHealthExporter.getInstance().collectData(e)
                        )
                )
        );
    }
}
