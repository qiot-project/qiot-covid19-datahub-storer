/**
 * 
 */
package io.qiot.covid19.datahub.storer.commons.exceptions;

/**
 * @author andreabattaglia
 *
 */
public class TelemetryDataValidationException extends Exception {

    public TelemetryDataValidationException() {
        super();
    }

    public TelemetryDataValidationException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public TelemetryDataValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public TelemetryDataValidationException(String message) {
        super(message);
    }

    public TelemetryDataValidationException(Throwable cause) {
        super(cause);
    }

}
