/**
 * 
 */
package org.qiot.covid19.datahub.storer.gas.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.qiot.covid19.datahub.storer.gas.domain.GasMeasurement;
import org.qiot.covid19.datahub.storer.gas.persistence.GasMeasurementRepository;
import org.qiot.covid19.datahub.storer.gas.util.event.MeasurementReceived;
import org.slf4j.Logger;


/**
 * @author abattagl
 *
 */
@ApplicationScoped
public class DataStoreServiceImpl implements DataStoreService {
    @Inject
    Logger LOGGER;

    @Inject
    GasMeasurementRepository repository;

    @PostConstruct
    void init() {

    }

    void HandleIncomingMessage(
            @Observes @MeasurementReceived GasMeasurement gm) {
        repository.save(gm);
    }

    @PreDestroy
    void destroy() {
    }
}
