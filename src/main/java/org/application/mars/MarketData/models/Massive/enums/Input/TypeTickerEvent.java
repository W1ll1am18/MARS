package org.application.mars.MarketData.models.Massive.enums.Input;

public enum TypeTickerEvent {
    TICKER_CHANGE;

    public String getValue() {
        return this.name().toLowerCase();
    }
}
