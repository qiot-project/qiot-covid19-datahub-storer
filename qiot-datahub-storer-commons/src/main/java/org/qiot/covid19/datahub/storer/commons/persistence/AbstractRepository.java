package org.qiot.covid19.datahub.storer.commons.persistence;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.qiot.covid19.datahub.storer.commons.domain.AbstractMeasurement;
import org.qiot.covid19.datahub.storer.commons.exceptions.DataServiceException;
import org.slf4j.Logger;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApi;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.exceptions.InfluxException;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

public abstract class AbstractRepository<M extends AbstractMeasurement> {

    @Inject
    Logger LOGGER;

//    public static final Long DATA_RETENTION_DAYS = -14L;
    protected InfluxDBClient influxDBClient;
    @ConfigProperty(name = "influxdb.connectionUrl")
    public String connectionUrl;
    @ConfigProperty(name = "influxdb.token")
    public String token;
    @ConfigProperty(name = "influxdb.orgId")
    public String orgId;
    @ConfigProperty(name = "influxdb.data.bucketId")
    public String bucketId;
    @ConfigProperty(name = "influxdb.data.bucketName")
    public String bucketName;

    public void save(M data) throws DataServiceException {
        try (WriteApi writeApi = influxDBClient.getWriteApi()) {
            writeApi.writeMeasurement(WritePrecision.NS, data);
        } catch (InfluxException ie) {
            throw new DataServiceException(
                    "Error while writing data to Influx: " + ie.getMessage());
        }
    }
}