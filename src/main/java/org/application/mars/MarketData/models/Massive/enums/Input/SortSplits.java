package org.application.mars.MarketData.models.Massive.enums.Input;

public enum SortSplits {
    EXECUTION_DATE,
    ADJUSTMENT_TYPE;

    public String getValue() {
        return this.name().toLowerCase();
    }
}
