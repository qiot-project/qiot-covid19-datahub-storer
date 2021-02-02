package org.qiot.covid19.datahub.storer.commons.domain;

import java.time.Instant;

import com.influxdb.annotations.Column;

public abstract class AbstractMeasurement {
    @Column(name = "station_id", tag = true)
    public String stationId;
    @Column(tag = true)
    public String serial;
    @Column(tag = true)
    public String name;
    @Column
    public double longitude;
    @Column
    public double latitude;
    @Column(tag = true)
    public String city;
    @Column(tag = true)
    public String country;

    @Column(timestamp = true)
    public Instant time;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((stationId == null) ? 0 : stationId.hashCode());
        result = prime * result + ((time == null) ? 0 : time.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractMeasurement other = (AbstractMeasurement) obj;
        if (stationId == null) {
            if (other.stationId != null)
                return false;
        } else if (!stationId.equals(other.stationId))
            return false;
        if (time == null) {
            if (other.time != null)
                return false;
        } else if (!time.equals(other.time))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AbstractMeasurement [stationId=");
        builder.append(stationId);
        builder.append(", serial=");
        builder.append(serial);
        builder.append(", name=");
        builder.append(name);
        builder.append(", longitude=");
        builder.append(longitude);
        builder.append(", latitude=");
        builder.append(", city=");
        builder.append(city);
        builder.append(", country=");
        builder.append(country);
        builder.append(", time=");
        builder.append(time);
        builder.append("]");
        return builder.toString();
    }

}
