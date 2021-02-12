package io.qiot.covid19.datahub.storer.pollution.util.converters;
///**
// * 
// */
//package org.qiot.covid19.datahub.storer.pollution.util.converters;
//
//import javax.enterprise.context.ApplicationScoped;
//import javax.json.JsonObject;
//
//import org.qiot.covid19.datahub.storer.commons.utils.AbstractTelemetryConverter;
//import org.qiot.covid19.datahub.storer.pollution.domain.PollutionTelemetry;
//
///**
// * @author abattagl
// *
// */
//@ApplicationScoped
//public class PollutionTelemetryConverter
//        extends AbstractTelemetryConverter<PollutionTelemetry> {
//
//    @Override
//    protected PollutionTelemetry newInstance() {
//        return new PollutionTelemetry();
//    }
//
//    @Override
//    protected void readMeasurementData(PollutionTelemetry telemetry,
//            JsonObject jsonObject) {
//        telemetry.pm1_0 = jsonObject.getJsonNumber("PM1_0").intValue();
//        telemetry.pm2_5 = jsonObject.getJsonNumber("PM2_5").intValue();
//        telemetry.pm10 = jsonObject.getJsonNumber("PM10").intValue();
//        telemetry.pm1_0_atm = jsonObject.getJsonNumber("PM1_0_atm").intValue();
//        telemetry.pm2_5_atm = jsonObject.getJsonNumber("PM2_5_atm").intValue();
//        telemetry.pm10_atm = jsonObject.getJsonNumber("PM10_atm").intValue();
//        telemetry.gt0_3um = jsonObject.getJsonNumber("gt0_3um").intValue();
//        telemetry.gt0_5um = jsonObject.getJsonNumber("gt0_5um").intValue();
//        telemetry.gt1_0um = jsonObject.getJsonNumber("gt1_0um").intValue();
//        telemetry.gt2_5um = jsonObject.getJsonNumber("gt2_5um").intValue();
//        telemetry.gt5_0um = jsonObject.getJsonNumber("gt5_0um").intValue();
//        telemetry.gt10um = jsonObject.getJsonNumber("gt10um").intValue();
//    }
//
//}
