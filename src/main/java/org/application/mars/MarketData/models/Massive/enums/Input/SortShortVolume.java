package org.application.mars.MarketData.models.Massive.enums.Input;

public enum SortShortVolume {
    TICKER,
    SHORT_VOLUME_RATIO,
    DATE;

    public String getValue() {
        return this.name().toLowerCase();
    }
}
