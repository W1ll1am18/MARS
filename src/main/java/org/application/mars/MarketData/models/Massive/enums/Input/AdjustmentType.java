package org.application.mars.MarketData.models.Massive.enums.Input;

public enum AdjustmentType {
    FORWARD_SPLIT,
    REVERSE_SPLIT,
    STOCK_DIVIDEND;

    public String getValue() {
        return this.name().toLowerCase();
    }
}
