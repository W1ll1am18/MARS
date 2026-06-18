package org.application.mars.MarketData.models.Massive.CorporateActions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Splits {
    @JsonProperty("adjustment_type")
    private String adjustmentType;

    @JsonProperty("execution_date")
    private String executionDate;

    @JsonProperty("historical_adjustment_factor")
    private Double historicalAdjustmentFactor;

    @JsonProperty("id")
    private String id;

    @JsonProperty("split_from")
    private Double splitFrom;

    @JsonProperty("split_to")
    private Double splitTo;

    @JsonProperty("ticker")
    private String ticker;
}
