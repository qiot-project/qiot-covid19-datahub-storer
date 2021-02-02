/**
 * 
 */
package org.qiot.covid19.datahub.storer.pollution.util.converters;

import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;

import org.qiot.covid19.datahub.storer.commons.utils.AbstractMeasurementConverter;
import org.qiot.covid19.datahub.storer.pollution.domain.PollutionMeasurement;

/**
 * @author abattagl
 *
 */
@ApplicationScoped
public class PollutionMeasurementConverter
        extends AbstractMeasurementConverter<PollutionMeasurement> {

    @Override
    protected PollutionMeasurement newInstance() {
        return new PollutionMeasurement();
    }

    @Override
    protected void readMeasurementData(PollutionMeasurement measurement,
            JsonObject jsonObject) {
        measurement.pm1_0 = jsonObject.getJsonNumber("PM1_0").intValue();
        measurement.pm2_5 = jsonObject.getJsonNumber("PM2_5").intValue();
        measurement.pm10 = jsonObject.getJsonNumber("PM10").intValue();
        measurement.pm1_0_atm = jsonObject.getJsonNumber("PM1_0_atm").intValue();
        measurement.pm2_5_atm = jsonObject.getJsonNumber("PM2_5_atm").intValue();
        measurement.pm10_atm = jsonObject.getJsonNumber("PM10_atm").intValue();
        measurement.gt0_3um = jsonObject.getJsonNumber("gt0_3um").intValue();
        measurement.gt0_5um = jsonObject.getJsonNumber("gt0_5um").intValue();
        measurement.gt1_0um = jsonObject.getJsonNumber("gt1_0um").intValue();
        measurement.gt2_5um = jsonObject.getJsonNumber("gt2_5um").intValue();
        measurement.gt5_0um = jsonObject.getJsonNumber("gt5_0um").intValue();
        measurement.gt10um = jsonObject.getJsonNumber("gt10um").intValue();
    }

}
