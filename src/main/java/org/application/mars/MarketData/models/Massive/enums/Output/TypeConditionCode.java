package org.application.mars.MarketData.models.Massive.enums.Output;

public enum TypeConditionCode {
    financial_status_indicator,
    market_condition,
    quote_condition,
    sale_condition,
    settlement_condition,
    short_sale_restriction_indicator,
    sip_generated_flag,
    trade_thru_exempt,
    buy_or_sell_side,
    regular;

    public String getValue() {
        return name();
    }
}
