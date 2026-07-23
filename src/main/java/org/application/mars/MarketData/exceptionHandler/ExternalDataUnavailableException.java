package org.application.mars.MarketData.exceptionHandler;

public class ExternalDataUnavailableException extends RuntimeException {
    public ExternalDataUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
