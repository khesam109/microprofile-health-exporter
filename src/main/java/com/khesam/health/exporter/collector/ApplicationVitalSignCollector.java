package com.khesam.health.exporter.collector;

import com.khesam.health.exporter.collector.model.VitalSign;
import com.khesam.health.exporter.config.ServiceScan;
import com.khesam.health.exporter.helper.HttpHelper;
import org.tinylog.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Date;
import java.util.List;

@Singleton
public class ApplicationVitalSignCollector implements VitalSignCollector {

    private final HttpHelper httpHelper;

    @Inject
    public ApplicationVitalSignCollector(HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    @Override
    public List<VitalSign> probeVitalSign(ServiceScan serviceScan) {
        Logger.info("Probing application vital sign collector has been started at {}", new Date().toString());

        List<VitalSign> vitalSigns = httpHelper.probeVitalSign(serviceScan);

        Logger.info("Probing application vital sign collector has been finished");
        return vitalSigns;
    }
}
