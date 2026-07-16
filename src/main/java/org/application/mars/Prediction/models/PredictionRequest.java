package org.application.mars.Prediction.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PredictionRequest {
    private Integer horizon;
    private Map<Long, String> sectors;
    private List<BarRequest> bars;
    @JsonProperty("voo_bars")
    private List<BarRequest> vooBars;
}
