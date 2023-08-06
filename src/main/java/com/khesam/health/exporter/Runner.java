package com.khesam.health.exporter;

import com.khesam.health.exporter.config.ConfigReader;
import com.khesam.health.exporter.config.ScheduleConfig;
import com.khesam.health.exporter.config.ServerConfig;
import com.khesam.health.exporter.config.ServiceScan;
import com.khesam.health.exporter.di.ApplicationDependencyComponent;
import com.khesam.health.exporter.di.DaggerApplicationDependencyComponent;
import com.khesam.health.exporter.scheduler.VitalSignCollectorScheduler;
import com.sun.net.httpserver.HttpServer;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.HTTPServer;
import org.tinylog.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Runner {

    public static void main(String[] args) {
        ApplicationDependencyComponent factory = DaggerApplicationDependencyComponent.create();
        ConfigReader configReader = factory.configReader();
        VitalSignCollectorScheduler vitalSignCollectorScheduler = factory.vitalSignCollectorScheduler();

        try {
            configReader.init();
            ServerConfig serverConfig = configReader.serverConfig();
            ScheduleConfig scheduleConfig = configReader.scheduleConfig();
            ServiceScan serviceScan = configReader.serviceScan();

            runServer(serverConfig);

            startScheduling(vitalSignCollectorScheduler, scheduleConfig, serviceScan);

        } catch (IOException ex) {
            Logger.error(ex, "Failed to read config");
            System.exit(1);
        } catch (IllegalStateException ex) {
            Logger.error("Please init config reader first");
            System.exit(1);
        }
    }

    private static void runServer(ServerConfig serverConfig) {
        Logger.info("Starting exporter...");

        try {
            HttpServer httpServer = HttpServer.create(
                    new InetSocketAddress(
                            serverConfig.port()
                    ), 3
            );

            httpServer.createContext(
                    serverConfig.contextRoot(),
                    new HTTPServer.HTTPMetricHandler(CollectorRegistry.defaultRegistry)
            );

            new HTTPServer.Builder()
                    .withHttpServer(httpServer)
                    .build();

            Logger.info("Http endpoint successfully started on port {}", serverConfig.port());
        } catch (IOException e) {
            Logger.error(e, "Failed to start http server");
        }
    }

    private static void startScheduling(
            VitalSignCollectorScheduler vitalSignCollectorScheduler,
            ScheduleConfig scheduleConfig,
            ServiceScan serviceScan
    ) {
        vitalSignCollectorScheduler.getVitalSignCollectorTaskRunner(scheduleConfig, serviceScan).run();
    }
}
