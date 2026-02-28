package org.application.mars.MarketData.models.Massive.enums.Input;

public enum Section {
    BUSINESS,
    RISK_FACTORS;

    public String getValue() {
        return this.name().toLowerCase();
    }
}
