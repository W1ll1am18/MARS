package org.application.mars.MarketData.models.Massive.enums.Input;

public enum SortShortInterest {
    DAYS_TO_COVER,
    SETTLEMENT_DATE,
    AVG_DAILY_VOLUME;

    public String getValue() {
        return this.name().toLowerCase();
    }
}
