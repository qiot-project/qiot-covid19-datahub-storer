package org.qiot.covid19.datahub.storer.gas.domain;

import java.time.Instant;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
@Measurement(name = "gas")
public class GasMeasurement {
    @Column(tag = true)
    public int stationId;

    @Column(tag = true)
    public Double adc;

    @Column(tag = true)
    public double nh3;

    @Column(tag = true)
    public double oxidising;

    @Column(tag = true)
    public double reducing;

    @Column(timestamp = true)
    public Instant time;

    @Override
    public String toString() {
        return "GasMeasurement [stationId=" + stationId + ", time=" + time
                + ", adc=" + adc + ", nh3=" + nh3 + ", oxidising=" + oxidising
                + ", reducing=" + reducing + "]";
    }

}
