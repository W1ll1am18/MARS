package org.application.mars.MarketData.models.Massive.CorporateActions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TickerEventsResponse {
    @JsonProperty("request_id")
    private String requestId;
    private TickerEvents results;
    private String status;
}
