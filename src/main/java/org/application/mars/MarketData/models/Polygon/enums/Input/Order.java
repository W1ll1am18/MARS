package org.application.mars.MarketData.models.Polygon.enums.Input;

//Input param for tickers
public enum Order {
    ASC,
    DESC;

    public String getValue() {
        return this.toString().toLowerCase();
    }
}
