package org.application.mars.MarketData.models.Massive.enums.Input;

public enum SortNews {
    PUBLISHED_UTC;

    public String getValue() {
        return this.name().toLowerCase();
    }
}
