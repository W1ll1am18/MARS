package org.application.mars.MarketData.models.Massive.enums.Input;

//Input params for market type
public enum Market {
    STOCKS,
    CRYPTO,
    FX,
    OTC,
    INDICES;

    public String getValue(){
        return this.toString().toLowerCase();
    }
}
