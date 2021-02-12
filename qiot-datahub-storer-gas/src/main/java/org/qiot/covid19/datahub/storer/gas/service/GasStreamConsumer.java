package org.qiot.covid19.datahub.storer.gas.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.qiot.covid19.datahub.storer.commons.exceptions.TelemetryDataValidationException;
import org.qiot.covid19.datahub.storer.gas.domain.GasTelemetry;
import org.qiot.covid19.datahub.storer.gas.util.event.MeasurementReceived;
import org.slf4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

@ApplicationScoped
public class GasStreamConsumer {

    @Inject
    Logger LOGGER;
    
    @Inject
    ObjectMapper MAPPER;

    @Inject
    @MeasurementReceived
    Event<GasTelemetry> measurementReceivedEvent;

    @Incoming("gas")
    public void process(String data) throws TelemetryDataValidationException {
        LOGGER.info("Consumed message {} from the GAS Stream", data);
//        GasTelemetry gm = converter.jsonToMeasurement(data);
        GasTelemetry gm;
        try {
            gm = MAPPER.readValue(data, GasTelemetry.class);
        } catch (Exception e) {
            throw new TelemetryDataValidationException(e);
        }
        measurementReceivedEvent.fire(gm);
    }

}
