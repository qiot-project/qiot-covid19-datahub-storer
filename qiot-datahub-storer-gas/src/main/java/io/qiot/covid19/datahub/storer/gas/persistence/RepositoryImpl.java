package io.qiot.covid19.datahub.storer.gas.persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.influxdb.client.InfluxDBClientFactory;

import io.qiot.covid19.datahub.storer.commons.persistence.AbstractRepository;
import io.qiot.covid19.datahub.storer.gas.domain.GasTelemetry;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class RepositoryImpl extends AbstractRepository<GasTelemetry> {


    @Inject
    Logger LOGGER;
    
    void onStart(@Observes StartupEvent ev) {
        
        LOGGER.info("Connecting to: {}, token: {}, org: {}, bucketName: {}",
                connectionUrl, token, orgId, bucketName);
        
        influxDBClient = InfluxDBClientFactory.create(connectionUrl,
                token.toCharArray(), orgId, bucketName);
        
        LOGGER.info("Connection health-check:\n{}",
                influxDBClient.health().toString());
        
    }

    void onStop(@Observes ShutdownEvent ev) {
        influxDBClient.close();
    }
}