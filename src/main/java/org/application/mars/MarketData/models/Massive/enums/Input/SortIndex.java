package org.application.mars.MarketData.models.Massive.enums.Input;

public enum SortIndex {
    CIK,
    TICKER,
    FORM_TYPE,
    FILING_DATE;

    public String getValue() {
        return this.name().toLowerCase();
    }
}
