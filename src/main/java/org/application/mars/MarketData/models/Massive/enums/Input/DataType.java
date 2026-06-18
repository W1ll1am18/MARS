package org.application.mars.MarketData.models.Massive.enums.Input;

public enum DataType {
    TRADE,
    BBO,
    NBBO;

    public String getValue() {
        return this.name().toLowerCase();
    }
}
