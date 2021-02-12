package io.qiot.covid19.datahub.storer.gas.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;

import io.qiot.covid19.datahub.storer.commons.domain.AbstractTelemetry;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
@Measurement(name = "gas")
public class GasTelemetry extends AbstractTelemetry {

    @JsonProperty(value = "adc")
    @Column
    public Double adc;
    @JsonProperty(value = "nh3")
    @Column
    public double nh3;
    @JsonProperty(value = "oxidising")
    @Column
    public double oxidising;
    @JsonProperty(value = "reducing")
    @Column
    public double reducing;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("GasTelemetry [stationId=");
        builder.append(stationId);
        builder.append(", time=");
        builder.append(time);
        builder.append(", serial=");
        builder.append(serial);
        builder.append(", name=");
        builder.append(name);
        builder.append(", longitude=");
        builder.append(longitude);
        builder.append(", latitude=");
        builder.append(latitude);
        builder.append(", city=");
        builder.append(city);
        builder.append(", country=");
        builder.append(country);
        builder.append(", ccode=");
        builder.append(ccode);
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
