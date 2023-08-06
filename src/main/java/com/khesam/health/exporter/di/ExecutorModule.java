package com.khesam.health.exporter.di;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Module
public class ExecutorModule {

    @Provides
    @Singleton
    ScheduledExecutorService provideScheduledExecutorService() {
        return Executors.newScheduledThreadPool(1);
    }
}
