package org.application.mars.MarketData.models.Massive.Fundamentals;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShortInterest {
    @JsonProperty("avg_daily_volume")
    private Long avgDailyVolume;

    @JsonProperty("days_to_cover")
    private Double daysToCover;

    @JsonProperty("settlement_date")
    private String settlementDate;

    @JsonProperty("short_interest")
    private Long shortInterest;

    @JsonProperty("ticker")
    private String ticker;
}
