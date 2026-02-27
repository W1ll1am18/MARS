package org.application.mars.MarketData.models.Massive.enums.Output;

public enum TypeExchange {
    SIP,
    ORF,
    TRF,
    exchange;

    public String getValue() {
        return name().toLowerCase();
    }
}
