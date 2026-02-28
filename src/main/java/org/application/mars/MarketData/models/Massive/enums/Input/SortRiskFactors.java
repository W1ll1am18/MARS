package org.application.mars.MarketData.models.Massive.enums.Input;

public enum SortRiskFactors {
    FILING_DATE,
    TICKER,
    CIK;

    public String getValue() {
        return this.name().toLowerCase();
    }
}
