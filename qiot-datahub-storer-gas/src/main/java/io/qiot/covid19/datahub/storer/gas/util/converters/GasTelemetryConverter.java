package io.qiot.covid19.datahub.storer.gas.util.converters;
///**
// * 
// */
//package org.qiot.covid19.datahub.storer.gas.util.converters;
//
//import javax.enterprise.context.ApplicationScoped;
//import javax.json.JsonObject;
//
//import org.qiot.covid19.datahub.storer.commons.domain.AbstractTelemetry;
//import org.qiot.covid19.datahub.storer.commons.utils.AbstractTelemetryConverter;
//import org.qiot.covid19.datahub.storer.gas.domain.GasTelemetry;
//
///**
// * @author andreabattaglia
// *
// */
//@ApplicationScoped
//public class GasTelemetryConverter
//        extends AbstractTelemetryConverter<GasTelemetry> {
//    
//    @Override
//    protected GasTelemetry newInstance() {
//        return new GasTelemetry();
//    }
//
//    @Override
//    protected void readMeasurementData(GasTelemetry telemetry,
//            JsonObject jsonObject) {
//        if (!jsonObject.isNull("adc"))
//            telemetry.adc = jsonObject.getJsonNumber("adc").doubleValue();
//        telemetry.nh3 = jsonObject.getJsonNumber("nh3").doubleValue();
//        telemetry.oxidising = jsonObject.getJsonNumber("oxidising").doubleValue();
//        telemetry.reducing = jsonObject.getJsonNumber("reducing").doubleValue();
//    }
//
//}
