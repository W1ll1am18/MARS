package org.application.mars.MarketData.models.Polygon.AggregateBars;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomBarsResponse {
    private String ticker;
    private String adjusted;
    private Integer queryCount;

    @JsonProperty("request_id")
    private String requestId;
    private Integer resultsCount;
    private String status;
    private ArrayList<CustomBars> results;

    @JsonProperty("next_url")
    private String nextUrl;
}
