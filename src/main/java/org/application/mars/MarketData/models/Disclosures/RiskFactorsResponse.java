package org.application.mars.MarketData.models.Disclosures;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RiskFactorsResponse {
    @JsonProperty("next_url")
    private String nextUrl;
    @JsonProperty("request_id")
    private String requestId;
    private ArrayList<RiskFactors> results;
    private String status;
}
