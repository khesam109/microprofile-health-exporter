package com.khesam.health.exporter.config;

import java.util.concurrent.TimeUnit;

public record ScheduleConfig(
        int period,
        TimeUnit unit
) {
}
