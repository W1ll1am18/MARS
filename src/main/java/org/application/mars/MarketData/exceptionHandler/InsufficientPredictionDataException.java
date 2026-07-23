package org.application.mars.MarketData.exceptionHandler;

public class InsufficientPredictionDataException extends RuntimeException {
    public InsufficientPredictionDataException(String ticker) {
        super("Insufficient peer data to generate prediction for: " + ticker);
    }
    public InsufficientPredictionDataException(String ticker, String reason) {
        super("Cannot generate prediction for " + ticker + ": " + reason);
    }
}
