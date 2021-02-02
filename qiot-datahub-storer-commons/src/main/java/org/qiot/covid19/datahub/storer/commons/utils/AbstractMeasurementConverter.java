/**
 * 
 */
package org.qiot.covid19.datahub.storer.commons.utils;

import java.io.StringReader;
import java.time.Instant;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.qiot.covid19.datahub.storer.commons.domain.AbstractMeasurement;

/**
 * @author abattagl
 *
 */
public abstract class AbstractMeasurementConverter<M extends AbstractMeasurement> {

    public M jsonToMeasurement(String data) {
        M gm = null;
        JsonObject jsonObject = null;
        // int stationId = 0;
        try (JsonReader reader = Json.createReader(new StringReader(data))) {
            gm = newInstance();

             jsonObject = reader.readObject();
            gm.time = Instant.parse(jsonObject.getString("instant"));

            jsonObject = readStationData(gm, jsonObject);

            readMeasurementData(gm, jsonObject);
        }
        return gm;
    }

    protected abstract void readMeasurementData(M measurement, JsonObject jsonObject);

    private JsonObject readStationData(M measurement, JsonObject jsonObject) {
        measurement.stationId = jsonObject.getString("stationId");
        measurement.stationId = jsonObject.getString("serial");
        measurement.stationId = jsonObject.getString("name");
        measurement.stationId = jsonObject.getString("city");
        measurement.stationId = jsonObject.getString("country");
        measurement.stationId = jsonObject.getString("ccode");
        return jsonObject;
    }

    protected abstract M newInstance();
}
