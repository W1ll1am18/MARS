package org.application.mars.MarketData.models.Massive.enums.Output;

public enum typeExchange {
    SIP,
    ORF,
    TRF,
    exchange;

    public String getValue() {
        return name().toLowerCase();
    }
}
