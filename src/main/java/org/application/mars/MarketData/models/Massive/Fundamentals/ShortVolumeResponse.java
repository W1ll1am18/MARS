package org.application.mars.MarketData.models.Massive.Fundamentals;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShortVolumeResponse {
    @JsonProperty("next_url")
    private String nextUrl;
    @JsonProperty("request_id")
    private String requestId;
    private ArrayList<ShortVolume> results;
    private String status;
}
