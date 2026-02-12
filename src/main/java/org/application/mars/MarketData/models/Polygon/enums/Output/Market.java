package org.application.mars.MarketData.models.Polygon.enums.Output;

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
}
