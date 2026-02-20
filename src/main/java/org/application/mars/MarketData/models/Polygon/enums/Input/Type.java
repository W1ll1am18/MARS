package org.application.mars.MarketData.models.Polygon.enums.Input;

//Input param for types of tickers //Note MUST STAY UPPERCASE
public enum Type {
    CS,
    ADRC,
    ADRP,
    ADRR,
    UNIT,
    RIGHT,
    PFD,
    FUND,
    SP,
    WARRANT,
    INDEX,
    ETF,
    ETN,
    OS,
    GDR,
    OTHER,
    NYSR,
    AGEN,
    EQLK,
    BOND,
    ADRW,
    BASKET,
    LT;

    public String getValue() {
        return this.toString();
    }
}
