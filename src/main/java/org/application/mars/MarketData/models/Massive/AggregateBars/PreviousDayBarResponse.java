package org.application.mars.MarketData.models.Massive.AggregateBars;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PreviousDayBarResponse {
    private String ticker;
    private Boolean adjusted;
    private Integer queryCount;

    @JsonProperty("request_id")
    private String requestId;
    private Integer resultsCount;
    private String status;
    private ArrayList<PreviousDayBar> results;
}
