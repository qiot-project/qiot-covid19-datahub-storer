package io.qiot.covid19.datahub.storer.gas.util.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.qiot.covid19.datahub.storer.commons.domain.AbstractTelemetry;

@ApplicationScoped
public class ObjectMapperProducer {

    private final ObjectMapper MAPPER;

    public ObjectMapperProducer() {
        MAPPER = new ObjectMapper();
        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
                .allowIfBaseType(AbstractTelemetry.class).build();
        MAPPER.activateDefaultTyping(ptv,
                ObjectMapper.DefaultTyping.OBJECT_AND_NON_CONCRETE);
        MAPPER.registerModule(new JavaTimeModule());
    }

    @Produces
    public ObjectMapper getObjectMapper(final InjectionPoint ip) {
        return MAPPER;
    }
}