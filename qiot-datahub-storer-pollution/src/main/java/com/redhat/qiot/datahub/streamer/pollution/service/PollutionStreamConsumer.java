package com.redhat.qiot.datahub.streamer.pollution.service;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;


import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;


@ApplicationScoped
public class PollutionStreamConsumer {

    @Inject
    Logger LOGGER;

    @Incoming("pollution")
    public void process(String data) {
        LOGGER.info("Consumed message {} from the POLLUTION Stream", data);
    }

}
