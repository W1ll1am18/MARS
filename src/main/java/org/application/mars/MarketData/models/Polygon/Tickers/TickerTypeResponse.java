package org.application.mars.MarketData.models.Polygon.Tickers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TickerTypeResponse {
    private Integer count;

    @JsonProperty("request_id")
    private String requestId;

    private ArrayList<TickerType> results;

    private String status;
}
