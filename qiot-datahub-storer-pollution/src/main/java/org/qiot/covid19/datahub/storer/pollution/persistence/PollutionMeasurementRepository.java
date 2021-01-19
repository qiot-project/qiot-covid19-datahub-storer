package org.qiot.covid19.datahub.storer.pollution.persistence;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.qiot.covid19.datahub.storer.pollution.domain.PollutionMeasurement;
import org.slf4j.Logger;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;

import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class PollutionMeasurementRepository {

    private final String DATABASE_NAME = "qiot";

    @ConfigProperty(name = "qiot.measurement.collection.coarse.name")
    String COLLECTION_NAME;

    @Inject
    Logger LOGGER;

    @Inject
    MongoClient mongoClient;

    MongoDatabase qiotDatabase;
    MongoCollection<PollutionMeasurement> pmCollection = null;
    CodecProvider pojoCodecProvider;
    CodecRegistry pojoCodecRegistry;

    void onStart(@Observes StartupEvent ev) {
    }

    @PostConstruct
    void init() {
        qiotDatabase = mongoClient.getDatabase(DATABASE_NAME);
        try {
            qiotDatabase.createCollection(COLLECTION_NAME);
        } catch (Exception e) {
            LOGGER.info("Collection {} already exists", COLLECTION_NAME);
        }
        pmCollection = qiotDatabase.getCollection(COLLECTION_NAME,
                PollutionMeasurement.class);
        /*
         * ensure indexes exist
         */
        ensureIndexes();

        // Create a CodecRegistry containing the PojoCodecProvider instance.
        pojoCodecProvider = PojoCodecProvider.builder()
                .register("org.qiot.covid19.datahub.storer.pollution.domain")
                .automatic(true).build();
        pojoCodecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(pojoCodecProvider));
        pmCollection = pmCollection.withCodecRegistry(pojoCodecRegistry);
    }

    private void ensureIndexes() {
        IndexOptions expirationOptionIndex = new IndexOptions().expireAfter(1L,
                TimeUnit.DAYS);
        pmCollection.createIndex(Indexes.ascending("time"),
                expirationOptionIndex);
        IndexOptions uniqueIndexOptions = new IndexOptions().unique(true);
        pmCollection.createIndex(Indexes.ascending("stationId", "time"),
                uniqueIndexOptions);
    }

    public void save(PollutionMeasurement pm) {
        LOGGER.info(
                "Persisting the measurement \n{}\nin the coarse collection",
                pm);
        try {
            pmCollection.insertOne(pm);
        } catch (Exception e) {
            LOGGER.error("An error occurred while persisting the measurement \n"
                    + pm.toString(), e);
        }
    }
}
