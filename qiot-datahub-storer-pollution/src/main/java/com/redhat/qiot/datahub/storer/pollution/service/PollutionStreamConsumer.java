package com.redhat.qiot.datahub.storer.pollution.service;

import java.io.StringReader;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;

import com.redhat.qiot.datahub.storer.pollution.domain.PollutionMeasurement;
import com.redhat.qiot.datahub.storer.pollution.util.event.MeasurementReceived;

@ApplicationScoped
public class PollutionStreamConsumer {

    @Inject
    Logger LOGGER;

    @Inject
    @MeasurementReceived
    Event<PollutionMeasurement> measurementReceivedEvent;

    @Incoming("pollution")
    public void process(String data) {
        LOGGER.info("Consumed message {} from the POLLUTION Stream", data);
        PollutionMeasurement gm = jsonToMeasurement(data);
        measurementReceivedEvent.fire(gm);
    }

    private PollutionMeasurement jsonToMeasurement(String data) {
        PollutionMeasurement pm = null;
        JsonObject jsonObject = null;
        // int stationId = 0;
        try (JsonReader reader = Json.createReader(new StringReader(data))) {
            pm = new PollutionMeasurement();
            jsonObject = reader.readObject();
            pm.stationId = jsonObject.getJsonNumber("stationId").intValue();

            pm.time =  Instant.parse(
                    jsonObject.getString("instant"));

            pm.pm1_0 = jsonObject.getJsonNumber("pm1_0").intValue();
            pm.pm2_5 = jsonObject.getJsonNumber("pm2_5").intValue();
            pm.pm10 = jsonObject.getJsonNumber("pm10").intValue();
            pm.pm1_0_atm = jsonObject.getJsonNumber("pm1_0_atm").intValue();
            pm.pm2_5_atm = jsonObject.getJsonNumber("pm2_5_atm").intValue();
            pm.pm10_atm = jsonObject.getJsonNumber("pm10_atm").intValue();
            pm.gt0_3um = jsonObject.getJsonNumber("gt0_3um").intValue();
            pm.gt0_5um = jsonObject.getJsonNumber("gt0_5um").intValue();
            pm.gt1_0um = jsonObject.getJsonNumber("gt1_0um").intValue();
            pm.gt2_5um = jsonObject.getJsonNumber("gt2_5um").intValue();
            pm.gt5_0um = jsonObject.getJsonNumber("gt5_0um").intValue();
            pm.gt10um = jsonObject.getJsonNumber("gt10um").intValue();
        }
        return pm;
    }

}
