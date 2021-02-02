/**
 * 
 */
package org.qiot.covid19.datahub.storer.gas.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.qiot.covid19.datahub.storer.commons.exceptions.DataServiceException;
import org.qiot.covid19.datahub.storer.gas.domain.GasMeasurement;
import org.qiot.covid19.datahub.storer.gas.persistence.RepositoryImpl;
import org.qiot.covid19.datahub.storer.gas.util.event.MeasurementReceived;
import org.slf4j.Logger;

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

    void HandleIncomingMessage(@Observes @MeasurementReceived GasMeasurement gm)
            throws DataServiceException {
        repository.save(gm);
    }
}
