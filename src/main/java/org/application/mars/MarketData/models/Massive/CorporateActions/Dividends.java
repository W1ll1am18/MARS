package org.application.mars.MarketData.models.Massive.CorporateActions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Dividends {
    @JsonProperty("cash_amount")
    private Double cashAmount;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("declaration_date")
    private String declarationDate;

    @JsonProperty("distribution_type")
    private String distributionType;

    @JsonProperty("ex_dividend_date")
    private String exDividendDate;

    @JsonProperty("frequency")
    private Integer frequency;

    @JsonProperty("historical_adjustment_factor")
    private Double historicalAdjustmentFactor;

    @JsonProperty("id")
    private String id;

    @JsonProperty("pay_date")
    private String payDate;

    @JsonProperty("record_date")
    private String recordDate;

    @JsonProperty("split_adjusted_cash_amount")
    private Double splitAdjustedCashAmount;

    @JsonProperty("ticker")
    private String ticker;
}
