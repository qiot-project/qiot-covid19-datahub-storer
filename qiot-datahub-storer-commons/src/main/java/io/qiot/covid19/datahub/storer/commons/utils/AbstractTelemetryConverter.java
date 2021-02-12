package io.qiot.covid19.datahub.storer.commons.utils;
///**
// * 
// */
//package org.qiot.covid19.datahub.storer.commons.utils;
//
//import java.io.StringReader;
//import java.time.Instant;
//
//import javax.json.Json;
//import javax.json.JsonObject;
//import javax.json.JsonReader;
//
//import org.qiot.covid19.datahub.storer.commons.domain.AbstractTelemetry;
//
///**
// * @author abattagl
// *
// */
//public abstract class AbstractTelemetryConverter<M extends AbstractTelemetry> {
//
//    public M jsonToMeasurement(String data) {
//        M gm = null;
//        JsonObject jsonObject = null;
//        // int stationId = 0;
//        try (JsonReader reader = Json.createReader(new StringReader(data))) {
//            gm = newInstance();
//
//             jsonObject = reader.readObject();
//            gm.time = Instant.parse(jsonObject.getString("instant"));
//
//            jsonObject = readStationData(gm, jsonObject);
//
//            readMeasurementData(gm, jsonObject);
//        }
//        return gm;
//    }
//
//    protected abstract void readMeasurementData(M measurement, JsonObject jsonObject);
//
//    private JsonObject readStationData(M telemetry, JsonObject jsonObject) {
//        telemetry.stationId = jsonObject.getString("stationId");
//        telemetry.stationId = jsonObject.getString("serial");
//        telemetry.stationId = jsonObject.getString("name");
//        telemetry.stationId = jsonObject.getString("city");
//        telemetry.stationId = jsonObject.getString("country");
//        telemetry.stationId = jsonObject.getString("ccode");
//        return jsonObject;
//    }
//
//    protected abstract M newInstance();
//}
