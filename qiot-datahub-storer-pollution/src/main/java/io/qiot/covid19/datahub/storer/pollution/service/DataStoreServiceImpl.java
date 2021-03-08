/**
 * 
 */
package io.qiot.covid19.datahub.storer.pollution.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;

import io.qiot.covid19.datahub.storer.commons.exceptions.DataServiceException;
import io.qiot.covid19.datahub.storer.pollution.domain.PollutionTelemetry;
import io.qiot.covid19.datahub.storer.pollution.persistence.RepositoryImpl;
import io.qiot.covid19.datahub.storer.pollution.util.event.MeasurementReceived;

/**
 * @author andreabattaglia
 *
 */
@ApplicationScoped
public class DataStoreServiceImpl {
    @Inject
    Logger LOGGER;

    @Inject
    RepositoryImpl repository;

    void HandleIncomingMessage(
            @Observes @MeasurementReceived PollutionTelemetry pm)
            throws DataServiceException {
        LOGGER.info("Received internal event with content {}", pm);
        repository.save(pm);
    }
}
