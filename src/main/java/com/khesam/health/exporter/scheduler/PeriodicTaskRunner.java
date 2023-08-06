package com.khesam.health.exporter.scheduler;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class PeriodicTaskRunner {

    private static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.DAYS;
    private final int period;
    private final TimeUnit timeUnit;
    private final Runnable task;
    private final ScheduledExecutorService executor;

    public  PeriodicTaskRunner(int period, TimeUnit timeUnit, Runnable task, ScheduledExecutorService executor) {
        this.period = period;
        this.timeUnit = timeUnit == null ? DEFAULT_TIME_UNIT : timeUnit;
        this.task = task;
        this.executor = executor;
    }

    public void run() {
        this.executor.scheduleAtFixedRate(
                this.task, 0, period, timeUnit
        );
    }
}
