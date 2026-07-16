package org.application.mars.Prediction.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PredictionResponse {
    @JsonProperty("model_info")
    private ModelInfo modelInfo;

    private List<PredictionResult> results;

    private List<Map<String, Object>> errors;
}