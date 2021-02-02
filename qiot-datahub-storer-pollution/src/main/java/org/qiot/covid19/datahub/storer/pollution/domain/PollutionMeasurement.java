package org.qiot.covid19.datahub.storer.pollution.domain;

import java.time.Instant;

import org.qiot.covid19.datahub.storer.commons.domain.AbstractMeasurement;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;

import io.quarkus.runtime.annotations.RegisterForReflection;

/*
 {
"stationId":int,
"instant":long,
"PM1_0":int,
"PM2_5":int,
"PM10":int,
"PM1_0_atm":int,
“PM2_5_atm":int,
"PM10_atm":int,
"gt0_3um":int,
"gt0_5um":int,
"gt1_0um":int,
"gt2_5um":int,
"gt5_0um":int
"gt10um":int
}
 */
@RegisterForReflection
@Measurement(name = "pollution")
public class PollutionMeasurement extends AbstractMeasurement {
    @Column
    public int pm1_0;
    @Column
    public int pm2_5;
    @Column
    public int pm10;
    @Column
    public int pm1_0_atm;
    @Column
    public int pm2_5_atm;
    @Column
    public int pm10_atm;
    @Column
    public int gt0_3um;
    @Column
    public int gt0_5um;
    @Column
    public int gt1_0um;
    @Column
    public int gt2_5um;
    @Column
    public int gt5_0um;
    @Column
    public int gt10um;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PollutionMeasurement [stationId=");
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
        builder.append(latitude);
        builder.append(", pm1_0=");
        builder.append(pm1_0);
        builder.append(", pm2_5=");
        builder.append(pm2_5);
        builder.append(", pm10=");
        builder.append(pm10);
        builder.append(", pm1_0_atm=");
        builder.append(pm1_0_atm);
        builder.append(", pm2_5_atm=");
        builder.append(pm2_5_atm);
        builder.append(", pm10_atm=");
        builder.append(pm10_atm);
        builder.append(", gt0_3um=");
        builder.append(gt0_3um);
        builder.append(", gt0_5um=");
        builder.append(gt0_5um);
        builder.append(", gt1_0um=");
        builder.append(gt1_0um);
        builder.append(", gt2_5um=");
        builder.append(gt2_5um);
        builder.append(", gt5_0um=");
        builder.append(gt5_0um);
        builder.append(", gt10um=");
        builder.append(gt10um);
        builder.append("]");
        return builder.toString();
    }

}
