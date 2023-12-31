package com.khesam.health.exporter.collector.model;

import java.util.EnumSet;

public enum HealthStatus {

    UNREACHABLE(-2), DOWN(-1), UNKWON(0), UP(1);
    public final int code;

    HealthStatus(int code) {
        this.code = code;
    }

    public static HealthStatus fromLiteral(String literal) {
        return EnumSet.allOf(HealthStatus.class).stream().
                filter(
                        e -> e.name().equalsIgnoreCase(literal)
                ).findAny().orElse(UNKWON);
    }
}
