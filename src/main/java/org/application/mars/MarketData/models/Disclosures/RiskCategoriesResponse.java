package org.application.mars.MarketData.models.Disclosures;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RiskCategoriesResponse {
    @JsonProperty("next_url")
    private String nextUrl;
    @JsonProperty("request_id")
    private String requestId;
    private ArrayList<RiskCategories> results;
    private String status;
}
