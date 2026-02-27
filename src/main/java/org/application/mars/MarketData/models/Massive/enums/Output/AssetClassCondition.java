package org.application.mars.MarketData.models.Massive.enums.Output;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AssetClassCondition {
    @JsonProperty("stocks")
    STOCKS,

    @JsonProperty("crypto")
    CRYPTO,

    @JsonProperty("fx")
    FX,

    @JsonProperty("options")
    OPTIONS;

    @JsonValue
    public String getValue() {
        return this.toString().toLowerCase();
    }
}
