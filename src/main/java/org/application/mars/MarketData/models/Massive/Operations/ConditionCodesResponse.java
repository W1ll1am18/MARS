package org.application.mars.MarketData.models.Massive.Operations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConditionCodesResponse {
    private Integer count;
    @JsonProperty("next_url")
    private String nextUrl;
    @JsonProperty("request_id")
    private String requestId;
    private ArrayList<ConditionCodes> results;
    private String status;
}
