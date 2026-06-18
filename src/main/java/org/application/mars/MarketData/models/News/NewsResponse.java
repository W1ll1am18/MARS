package org.application.mars.MarketData.models.News;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsResponse {
    @JsonProperty("count")
    private Integer count;

    @JsonProperty("next_url")
    private String nextUrl;

    @JsonProperty("request_id")
    private String requestId;

    @JsonProperty("results")
    private ArrayList<News> results;
    private String status;
}
