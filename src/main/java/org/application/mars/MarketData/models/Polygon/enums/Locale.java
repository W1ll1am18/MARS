package org.application.mars.MarketData.models.Polygon.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Locale {
    @JsonProperty("global")
    GLOBAL,

    @JsonProperty("us")
    US;

    @JsonValue
    public String getValue() {
        return this.toString().toLowerCase();
    }

    @JsonCreator
    public static Locale from(String value) {
        return Locale.valueOf(value.toUpperCase());
    }
}
