package com.redhat.qiot.datahub.streamer.gas.service;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;


import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;


@ApplicationScoped
public class GasStreamConsumer {

    @Inject
    Logger LOGGER;

    @Incoming("gas")
    public void process(String data) {
        LOGGER.info("Consumed message {} from the GAS Stream", data);
    }

}
