package org.qiot.covid19.datahub.storer.gas.service;

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
import org.qiot.covid19.datahub.storer.gas.domain.GasMeasurement;
import org.qiot.covid19.datahub.storer.gas.util.event.MeasurementReceived;
import org.slf4j.Logger;

/*
{
"stationId":int,
"instant":long,
"adc":double,
"nh3":double,
“oxidising":double,
"reducing":double
}
 */
@ApplicationScoped
public class GasStreamConsumer {

    @Inject
    Logger LOGGER;

    @Inject
    @MeasurementReceived
    Event<GasMeasurement> measurementReceivedEvent;

    @Incoming("gas")
    public void process(String data) {
        LOGGER.info("Consumed message {} from the GAS Stream", data);
        GasMeasurement gm = jsonToGMeasurement(data);
        measurementReceivedEvent.fire(gm);
    }

    private GasMeasurement jsonToGMeasurement(String data) {
        GasMeasurement gm = null;
        JsonObject jsonObject = null;
        // int stationId = 0;
        try (JsonReader reader = Json.createReader(new StringReader(data))) {
            gm = new GasMeasurement();
            jsonObject = reader.readObject();
            gm.stationId = jsonObject.getJsonNumber("stationId").intValue();

            gm.time = Instant.parse(jsonObject.getString("instant"));

            if (jsonObject.containsKey("adc"))
                gm.adc = jsonObject.getJsonNumber("adc").doubleValue();
            gm.nh3 = jsonObject.getJsonNumber("nh3").doubleValue();
            gm.oxidising = jsonObject.getJsonNumber("oxidising").doubleValue();
            gm.reducing = jsonObject.getJsonNumber("reducing").doubleValue();
        } catch (Exception e) {
            LOGGER.error(
                    "An error occurred processing the message paylod " + data,
                    e);
        }
        
        return gm;
    }

}
