package org.application.mars.MarketData.models.Massive.enums.Output;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Sentiment {
    @JsonProperty("negative")
    NEGATIVE,

    @JsonProperty("neutral")
    NEUTRAL,

    @JsonProperty("positive")
    POSITIVE;

    public String getValue() {
        return this.toString().toLowerCase();
    }
}