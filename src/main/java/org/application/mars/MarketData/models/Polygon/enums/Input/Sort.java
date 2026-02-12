package org.application.mars.MarketData.models.Polygon.enums.Input;

//Input params for field sorting of tickers used for ordering
public enum Sort {
    TICKER,
    NAME,
    MARKET,
    LOCALE,
    PRIMARY_EXCHANGE,
    TYPE,
    CURRENCY_SYMBOL,
    CURRENCY_NAME,
    BASE_CURRENCY_SYMBOL,
    BASE_CURRENCY_NAME,
    CIK,
    COMPOSITE_FIGI,
    SHARE_CLASS_FIGI,
    LAST_UPDATED_UTC,
    DELISTED_UTC;

    public String getValue(){
        return this.toString().toLowerCase();
    }
}
