package org.application.mars.MarketData.models.Massive.enums.Output;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TypeConditionCode {
    @JsonProperty("financial_status_indicator")
    FINANCIAL_STATUS_INDICATOR,

    @JsonProperty("market_condition")
    MARKET_CONDITION,

    @JsonProperty("quote_condition")
    QUOTE_CONDITION,

    @JsonProperty("sale_condition")
    SALE_CONDITION,

    @JsonProperty("settlement_condition")
    SETTLEMENT_CONDITION,

    @JsonProperty("short_sale_restriction_indicator")
    SHORT_SALE_RESTRICTION_INDICATOR,

    @JsonProperty("sip_generated_flag")
    SIP_GENERATED_FLAG,

    @JsonProperty("trade_thru_exempt")
    TRADE_THRU_EXEMPT,

    @JsonProperty("buy_or_sell_side")
    BUY_OR_SELL_SIDE,

    @JsonProperty("regular")
    REGULAR;

    public String getValue() {
        return name();
    }
}
