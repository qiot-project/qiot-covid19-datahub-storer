package io.qiot.covid19.datahub.storer.pollution.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.qiot.covid19.datahub.storer.commons.exceptions.TelemetryDataValidationException;
import io.qiot.covid19.datahub.storer.pollution.domain.PollutionTelemetry;
import io.qiot.covid19.datahub.storer.pollution.util.event.MeasurementReceived;

@ApplicationScoped
public class PollutionStreamConsumer {

    @Inject
    Logger LOGGER;

    @Inject
    ObjectMapper MAPPER;

    @Inject
    @MeasurementReceived
    Event<PollutionTelemetry> measurementReceivedEvent;

    @Incoming("pollution")
    public void process(String data) throws TelemetryDataValidationException {
        LOGGER.info("Consumed message {} from the POLLUTION Stream", data);
        // PollutionTelemetry gm = converter.jsonToMeasurement(data);
        PollutionTelemetry pm;
        try {
            pm = MAPPER.readValue(data, PollutionTelemetry.class);
        } catch (Exception e) {
            throw new TelemetryDataValidationException(e);
        }
        measurementReceivedEvent.fire(pm);
    }
}
