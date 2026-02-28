package org.application.mars.MarketData.models.Massive.enums.Output;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum IPOStatus {
    @JsonProperty("direct_listing_process")
    DIRECT_LISTING_PROCESS,

    @JsonProperty("history")
    HISTORY,

    @JsonProperty("new")
    NEW,

    @JsonProperty("pending")
    PENDING,

    @JsonProperty("postponed")
    POSTPONED,

    @JsonProperty("rumor")
    RUMOR,

    @JsonProperty("withdrawn")
    WITHDRAWN;

    public String getValue() {
        return this.name().toLowerCase();
    }
}
