package org.application.mars.MarketData.models.Polygon.enums.Input;

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
