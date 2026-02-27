package org.application.mars.MarketData.models.Massive.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AssetClassExchange {
    @JsonProperty("stocks")
    STOCKS,

    @JsonProperty("crypto")
    CRYPTO,

    @JsonProperty("fx")
    FX,

    @JsonProperty("futures")
    FUTURES,

    @JsonProperty("options")
    OPTIONS;

    @JsonValue
    public String getValue() {
        return this.toString().toLowerCase();
    }
}
