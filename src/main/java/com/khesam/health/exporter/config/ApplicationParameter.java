package com.khesam.health.exporter.config;

import java.util.Collections;
import java.util.List;

public class ApplicationParameter {

    private static final ScanInterval DEFAULT_INTERVAL = new ScanInterval(1, "HOUR");
    private static ScanInterval scanInterval;
    private static List<Service> services;
    private static ServerConfig serverConfig;

    public static void registerService(List<Service> services) {
        if (services != null && !services.isEmpty()) {
            ApplicationParameter.services = services;
        } else {
            ApplicationParameter.services = Collections.emptyList();
        }
    }

    public static void setServerConfig(ServerConfig serverConfig) {
        ApplicationParameter.serverConfig = serverConfig;
    }

    public static void setScanInterval(ScanInterval scanInterval) {
        if (scanInterval == null) {
            ApplicationParameter.scanInterval = DEFAULT_INTERVAL;
        } else if (scanInterval.unit() == null) {
            ApplicationParameter.scanInterval = new ScanInterval(scanInterval.period(), "HOUR");
        } else {
            ApplicationParameter.scanInterval = scanInterval;
        }
    }

    public static ScanInterval scanInterval() {
        return ApplicationParameter.scanInterval;
    }

    public static List<Service> services() {
        return ApplicationParameter.services;
    }

    public static ServerConfig serverConfig() {
        return ApplicationParameter.serverConfig;
    }
}
