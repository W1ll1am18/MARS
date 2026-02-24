package org.application.mars.MarketData.models.Polygon.enums.Input;

public enum SeriesType {
    OPEN,
    HIGH,
    LOW,
    CLOSE;

    public String getValue() {
        return this.name().toLowerCase();
    }
}
