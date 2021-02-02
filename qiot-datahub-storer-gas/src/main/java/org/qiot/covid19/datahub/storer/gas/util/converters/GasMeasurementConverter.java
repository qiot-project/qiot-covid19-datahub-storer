/**
 * 
 */
package org.qiot.covid19.datahub.storer.gas.util.converters;

import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;

import org.qiot.covid19.datahub.storer.commons.domain.AbstractMeasurement;
import org.qiot.covid19.datahub.storer.commons.utils.AbstractMeasurementConverter;
import org.qiot.covid19.datahub.storer.gas.domain.GasMeasurement;

/**
 * @author abattagl
 *
 */
@ApplicationScoped
public class GasMeasurementConverter
        extends AbstractMeasurementConverter<GasMeasurement> {
    
    @Override
    protected GasMeasurement newInstance() {
        return new GasMeasurement();
    }

    @Override
    protected void readMeasurementData(GasMeasurement measurement,
            JsonObject jsonObject) {
        if (!jsonObject.isNull("adc"))
            measurement.adc = jsonObject.getJsonNumber("adc").doubleValue();
        measurement.nh3 = jsonObject.getJsonNumber("nh3").doubleValue();
        measurement.oxidising = jsonObject.getJsonNumber("oxidising").doubleValue();
        measurement.reducing = jsonObject.getJsonNumber("reducing").doubleValue();
    }

}
