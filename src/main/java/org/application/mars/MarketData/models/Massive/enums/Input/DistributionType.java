package org.application.mars.MarketData.models.Massive.enums.Input;

public enum DistributionType {
    RECURRING,
    SPECIAL,
    SUPPLEMENTAL,
    IRREGULAR,
    UNKNOWN;

    public String getValue() {
        return this.name().toLowerCase();
    }
}
