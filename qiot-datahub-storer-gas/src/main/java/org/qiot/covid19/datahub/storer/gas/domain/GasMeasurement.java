package org.qiot.covid19.datahub.storer.gas.domain;

import org.qiot.covid19.datahub.storer.commons.domain.AbstractMeasurement;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
@Measurement(name = "gas")
public class GasMeasurement extends AbstractMeasurement {

    @Column
    public Double adc;

    @Column
    public double nh3;

    @Column
    public double oxidising;

    @Column
    public double reducing;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("GasMeasurement [stationId=");
        builder.append(stationId);
        builder.append(", serial=");
        builder.append(serial);
        builder.append(", name=");
        builder.append(name);
        builder.append(", city=");
        builder.append(city);
        builder.append(", country=");
        builder.append(country);
        builder.append(", time=");
        builder.append(time);
        builder.append(", adc=");
        builder.append(adc);
        builder.append(", nh3=");
        builder.append(nh3);
        builder.append(", oxidising=");
        builder.append(oxidising);
        builder.append(", reducing=");
        builder.append(reducing);
        builder.append("]");
        return builder.toString();
    }

}
