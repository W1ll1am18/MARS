package org.application.mars.MarketData.models.Massive.enums.Output;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TypeExchange {
    SIP,
    ORF,
    TRF,
    @JsonProperty("exchange")
    EXCHANGE;

    public String getValue() {
        return name().toLowerCase();
    }
}
