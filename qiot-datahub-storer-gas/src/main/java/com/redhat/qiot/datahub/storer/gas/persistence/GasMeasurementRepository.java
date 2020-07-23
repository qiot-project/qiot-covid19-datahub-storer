package com.redhat.qiot.datahub.storer.gas.persistence;

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
import org.slf4j.Logger;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import com.redhat.qiot.datahub.storer.gas.domain.GasMeasurement;

import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class GasMeasurementRepository {

    private final String DATABASE_NAME = "qiot";

    @ConfigProperty(name = "qiot.measurement.collection.coarse.name")
    String COLLECTION_NAME;

    @Inject
    Logger LOGGER;

    @Inject
    MongoClient mongoClient;

    MongoDatabase qiotDatabase;
    MongoCollection<GasMeasurement> gmCollection = null;
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
        gmCollection = qiotDatabase.getCollection(COLLECTION_NAME,
                GasMeasurement.class);
        /*
         * ensure indexes exist
         */
        ensureIndexes();

        // Create a CodecRegistry containing the PojoCodecProvider instance.
        pojoCodecProvider = PojoCodecProvider.builder()
                .register("com.redhat.qiot.datahub.streamer.gas.domain")
                .automatic(true).build();
        pojoCodecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(pojoCodecProvider));
        gmCollection = gmCollection.withCodecRegistry(pojoCodecRegistry);
    }

    private void ensureIndexes() {
        IndexOptions expirationOptionIndex = new IndexOptions().expireAfter(1L,
                TimeUnit.DAYS);
        gmCollection.createIndex(Indexes.ascending("time"),
                expirationOptionIndex);
        IndexOptions uniqueIndexOptions = new IndexOptions().unique(true);
        gmCollection.createIndex(Indexes.ascending("stationId", "time"),
                uniqueIndexOptions);
    }

    public void save(GasMeasurement gm) {
        LOGGER.info("Persisting the measurement \n{}\nin the coarse collection",
                gm);
        try {
            gmCollection.insertOne(gm);
        } catch (Exception e) {
            LOGGER.error("An error occurred while persisting the measurement \n"
                    + gm.toString(), e);
        }
    }
}
