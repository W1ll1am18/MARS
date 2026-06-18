package org.application.mars.MarketData.models.Massive.Fundamentals;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Float {
    @JsonProperty("effective_date")
    private String effectiveDate;

    @JsonProperty("free_float")
    private Long freeFloat;

    @JsonProperty("free_float_percent")
    private Double freeFloatPercent;

    @JsonProperty("ticker")
    private String ticker;
}
