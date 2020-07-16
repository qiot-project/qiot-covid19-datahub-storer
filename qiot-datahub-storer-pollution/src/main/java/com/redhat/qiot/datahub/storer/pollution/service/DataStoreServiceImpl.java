/**
 * 
 */
package com.redhat.qiot.datahub.storer.pollution.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.redhat.qiot.datahub.storer.pollution.domain.PollutionMeasurement;
import com.redhat.qiot.datahub.storer.pollution.persistence.PollutionMeasurementRepository;
import com.redhat.qiot.datahub.storer.pollution.util.event.MeasurementReceived;

/**
 * @author abattagl
 *
 */
@ApplicationScoped
public class DataStoreServiceImpl implements DataStoreService {
    @Inject
    Logger LOGGER;

    @Inject
    PollutionMeasurementRepository repository;

    @PostConstruct
    void init() {

    }

    void HandleIncomingMessage(
            @Observes @MeasurementReceived PollutionMeasurement pm) {
        LOGGER.info("Received internal event with content {}", pm);
        repository.save(pm);
    }

    @PreDestroy
    void destroy() {
    }
}
