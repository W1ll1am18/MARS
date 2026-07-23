package org.application.mars.MarketData.exceptionHandler;

public class TickerNotFoundException extends RuntimeException {
    public TickerNotFoundException(String ticker) {
        super("Ticker not found or unsupported: " + ticker);
    }
}
