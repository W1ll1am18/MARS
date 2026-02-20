package org.application.mars.MarketData.models.Polygon.Tickers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TickerOverviewResponse {
    private Integer count;

    @JsonProperty("request_id")
    private String requestId;

    private TickerOverview results;

    private String status;
}
