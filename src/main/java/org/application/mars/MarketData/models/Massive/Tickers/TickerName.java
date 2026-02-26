package org.application.mars.MarketData.models.Massive.Tickers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TickerName {
    @JsonProperty("ticker")
    private String tickerName;
}
