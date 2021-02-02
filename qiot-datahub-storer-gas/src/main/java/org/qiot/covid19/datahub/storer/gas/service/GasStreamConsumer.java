package org.qiot.covid19.datahub.storer.gas.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.qiot.covid19.datahub.storer.gas.domain.GasMeasurement;
import org.qiot.covid19.datahub.storer.gas.util.converters.GasMeasurementConverter;
import org.qiot.covid19.datahub.storer.gas.util.event.MeasurementReceived;
import org.slf4j.Logger;

@ApplicationScoped
public class GasStreamConsumer {

    @Inject
    Logger LOGGER;

    @Inject
    GasMeasurementConverter converter;

    @Inject
    @MeasurementReceived
    Event<GasMeasurement> measurementReceivedEvent;

    @Incoming("gas")
    public void process(String data) {
        LOGGER.info("Consumed message {} from the GAS Stream", data);
        GasMeasurement gm = converter.jsonToMeasurement(data);
        measurementReceivedEvent.fire(gm);
    }

}
