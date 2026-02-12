package org.application.mars.MarketData.models.Polygon.enums.Output;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Locale {
    @JsonProperty("global")
    GLOBAL,

    @JsonProperty("us")
    US;

    @JsonValue
    public String getName() {
        return this.name().toLowerCase();
    }
}
