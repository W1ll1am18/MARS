package org.application.mars.MarketData.models.Massive.enums.Input;

public enum SortConditionCodes {
    ASSET_CLASS,
    ID,
    TYPE,
    NAME,
    DATA_TYPES,
    LEGACY;

    public String getValue() {
        return this.name().toLowerCase();
    }
}
