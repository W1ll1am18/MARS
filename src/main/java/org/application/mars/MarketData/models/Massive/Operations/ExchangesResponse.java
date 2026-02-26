package org.application.mars.MarketData.models.Massive.Operations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangesResponse {
    private Integer count;
    @JsonProperty("request_id")
    private String requestId;
    private ArrayList<Exchanges> results;
    private String status;
}
