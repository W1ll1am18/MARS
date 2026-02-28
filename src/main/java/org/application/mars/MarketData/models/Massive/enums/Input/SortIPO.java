package org.application.mars.MarketData.models.Massive.enums.Input;

public enum SortIPO {
    LISTING_DATE,
    TICKER,
    LAST_UPDATED,
    SECURITY_TYPE,
    ISSUER_NAME,
    CURRENCY_CODE,
    ISIN,
    US_CODE,
    FINAL_ISSUE_PRICE,
    MIN_SHARES_OFFERED,
    MAX_SHARES_OFFERED,
    LOWEST_OFFER_PRICE,
    HIGHEST_OFFER_PRICE,
    TOTAL_OFFER_SIZE,
    SHARES_OUTSTANDING,
    PRIMARY_EXCHANGE,
    LOT_SIZE,
    SECURITY_DESCRIPTION,
    IPO_STATUS,
    ANNOUNCED_DATE;

    public String getValue() {
        return this.name().toLowerCase();
    }
}
