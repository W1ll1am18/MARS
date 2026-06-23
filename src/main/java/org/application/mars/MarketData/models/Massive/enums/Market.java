package org.application.mars.MarketData.models.Massive.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Market {
    @JsonProperty("stocks")
    STOCKS,
    @JsonProperty("crypto")
    CRYPTO,
    @JsonProperty("fx")
    FX,
    @JsonProperty("indices")
    INDICES,
    @JsonProperty("otc")
    OTC;

    @JsonValue
    public String getName() {
        return this.name().toLowerCase();
    }

    @JsonCreator
    public static Market from(String value) {
        return Market.valueOf(value.toUpperCase());
    }
}
