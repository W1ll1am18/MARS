package org.application.mars.MarketData.models.Massive.enums.Input;

//Input param for tickers
public enum Order {
    ASC,
    DESC;

    public String getValue() {
        return this.toString().toLowerCase();
    }
}
