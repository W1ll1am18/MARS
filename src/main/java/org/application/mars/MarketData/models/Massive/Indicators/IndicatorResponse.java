package org.application.mars.MarketData.models.Massive.Indicators;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IndicatorResponse {
    @JsonProperty("next_url")
    private String nextUrl;

    @JsonProperty("request_id")
    private String requestId;
    private Indicator results;
    private String status;
}
