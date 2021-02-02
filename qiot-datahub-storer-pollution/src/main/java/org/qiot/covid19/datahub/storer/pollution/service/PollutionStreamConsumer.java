package org.qiot.covid19.datahub.storer.pollution.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.qiot.covid19.datahub.storer.pollution.domain.PollutionMeasurement;
import org.qiot.covid19.datahub.storer.pollution.util.converters.PollutionMeasurementConverter;
import org.qiot.covid19.datahub.storer.pollution.util.event.MeasurementReceived;
import org.slf4j.Logger;

@ApplicationScoped
public class PollutionStreamConsumer {

    @Inject
    Logger LOGGER;

    @Inject
    PollutionMeasurementConverter converter;

    @Inject
    @MeasurementReceived
    Event<PollutionMeasurement> measurementReceivedEvent;

    @Incoming("pollution")
    public void process(String data) {
        LOGGER.info("Consumed message {} from the POLLUTION Stream", data);
        PollutionMeasurement gm = converter.jsonToMeasurement(data);
        measurementReceivedEvent.fire(gm);
    }
}
