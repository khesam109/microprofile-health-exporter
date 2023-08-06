package com.khesam.health.exporter.collector;

import com.khesam.health.exporter.collector.model.VitalSign;
import com.khesam.health.exporter.config.ServiceScan;

import java.util.List;

public interface VitalSignCollector {

    List<VitalSign> probeVitalSign(ServiceScan serviceScan);
}
