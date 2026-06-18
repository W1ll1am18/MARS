package org.application.mars.MarketData.models.Massive.enums.Input;

public enum SortFloat {
    TICKER,
    FREE_FLOAT_PERCENT;

    public String getValue() {
        return this.name().toLowerCase();
    }
}
