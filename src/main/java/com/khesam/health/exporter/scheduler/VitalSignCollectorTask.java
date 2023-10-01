package com.khesam.health.exporter.scheduler;

import com.khesam.health.exporter.collector.VitalSignCollector;
import com.khesam.health.exporter.collector.model.VitalSign;
import com.khesam.health.exporter.config.ServiceScan;
import com.khesam.health.exporter.scheduler.callback.VitalSignCollectorCallback;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import org.tinylog.Logger;

import java.util.Date;
import java.util.List;

public class VitalSignCollectorTask implements Runnable {

    private final ServiceScan serviceScan;
    private final VitalSignCollector vitalSignCollector;
    private final VitalSignCollectorCallback vitalSignCollectorCallback;

    @AssistedInject
    public VitalSignCollectorTask(
            @Assisted ServiceScan serviceScan,
            VitalSignCollector vitalSignCollector,
            VitalSignCollectorCallback vitalSignCollectorCallback
    ) {
        this.serviceScan = serviceScan;
        this.vitalSignCollector = vitalSignCollector;
        this.vitalSignCollectorCallback = vitalSignCollectorCallback;
    }

    @Override
    public void run() {
        Logger.info("Vital sign collector scheduler has been started at {}", new Date().toString());

        List<VitalSign> vitalSigns = vitalSignCollector.probeVitalSign(serviceScan);
        vitalSignCollectorCallback.onSuccess(vitalSigns);

        Logger.info("Vital sign collector scheduler just finished at {}", new Date().toString());
    }
}
