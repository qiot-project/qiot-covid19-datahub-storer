package org.qiot.covid19.datahub.storer.pollution.persistence;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.qiot.covid19.datahub.storer.pollution.domain.PollutionMeasurement;
import org.qiot.covid19.datahub.storer.pollution.util.exceptions.DataServiceException;
import org.slf4j.Logger;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApi;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.exceptions.InfluxException;

@ApplicationScoped
public class RepositoryImpl {

    @Inject
    Logger LOGGER;

    public static final Long DATA_RETENTION_DAYS = -14L;
    private InfluxDBClient influxDBClient;
    @ConfigProperty(name = "influxdb.connectionUrl")
    String connectionUrl;
    @ConfigProperty(name = "influxdb.token")
    String token;
    @ConfigProperty(name = "influxdb.orgId")
    String orgId;
    @ConfigProperty(name = "influxdb.data.bucketId")
    String bucketId;
    @ConfigProperty(name = "influxdb.data.bucketName")
    String bucketName;

    @PostConstruct
    private void initializeInfluxDBClient() {
        LOGGER.info("Connecting to: {}, token: {}, org: {}, bucketId: {}",
                connectionUrl, token, orgId, bucketId);
        influxDBClient = InfluxDBClientFactory.create(connectionUrl,
                token.toCharArray(), orgId, bucketId);
    }

    @PreDestroy
    public void close() throws Exception {
        influxDBClient.close();
    }

    public void save(PollutionMeasurement data) throws DataServiceException {
        try (WriteApi writeApi = influxDBClient.getWriteApi()) {
            writeApi.writeMeasurement(WritePrecision.NS, data);
        } catch (InfluxException ie) {
            throw new DataServiceException(
                    "Error while writing data to Influx: " + ie.getMessage());
        }
    }
}