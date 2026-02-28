package org.application.mars.MarketData.models.Massive.enums.Input;

public enum SortDividend {
    TICKER,
    EX_DIVIDEND_DATE,
    FREQUENCY,
    DISTRIBUTION_TYPE;

    public String getValue() {
        return this.name().toLowerCase();
    }
}
