/**
 * 
 */
package io.qiot.covid19.datahub.storer.gas.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;

import io.qiot.covid19.datahub.storer.commons.exceptions.DataServiceException;
import io.qiot.covid19.datahub.storer.gas.domain.GasTelemetry;
import io.qiot.covid19.datahub.storer.gas.persistence.RepositoryImpl;
import io.qiot.covid19.datahub.storer.gas.util.event.MeasurementReceived;

/**
 * @author abattagl
 *
 */
@ApplicationScoped
public class DataStoreServiceImpl {
    @Inject
    Logger LOGGER;

    @Inject
    RepositoryImpl repository;

    void HandleIncomingMessage(@Observes @MeasurementReceived GasTelemetry gm)
            throws DataServiceException {
        repository.save(gm);
    }
}
