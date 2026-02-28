package org.application.mars.MarketData.models.Massive.enums.Input;

public enum SortRiskCategory {
    TAXONOMY,
    PRIMARY_CATEGORY,
    SECONDARY_CATEGORY,
    TERTIARY_CATEGORY;

    public String getValue() {
        return this.name().toLowerCase();
    }
}
