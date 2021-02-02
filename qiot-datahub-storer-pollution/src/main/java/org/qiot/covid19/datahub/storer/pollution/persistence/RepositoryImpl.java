package org.qiot.covid19.datahub.storer.pollution.persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.qiot.covid19.datahub.storer.commons.persistence.AbstractRepository;
import org.qiot.covid19.datahub.storer.pollution.domain.PollutionMeasurement;
import org.slf4j.Logger;

import com.influxdb.client.InfluxDBClientFactory;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class RepositoryImpl extends AbstractRepository<PollutionMeasurement> {


    @Inject
    Logger LOGGER;
    
    void onStart(@Observes StartupEvent ev) {
        LOGGER.info("Connecting to: {}, token: {}, org: {}, bucketId: {}",
                connectionUrl, token, orgId, bucketId);
        influxDBClient = InfluxDBClientFactory.create(connectionUrl,
                token.toCharArray(), orgId, bucketId);
        LOGGER.info("Connection successfull:\n{}",
                influxDBClient.health().toString());
    }

    void onStop(@Observes ShutdownEvent ev) {
        influxDBClient.close();
    }
}