package org.application.mars.MarketData.models.Massive.enums.Input;

public enum SortTenK {
    CIK,
    TICKER,
    FILING_DATE,
    PERIOD_END;

    public String getValue() {
        return this.name().toLowerCase();
    }
}
