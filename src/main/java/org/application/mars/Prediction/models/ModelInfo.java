package org.application.mars.Prediction.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModelInfo {
    private Integer horizon;

    @JsonProperty("model_version")
    private String modelVersion;

    @JsonProperty("trained_at_utc")
    private String trainedAtUtc;

    @JsonProperty("confirmed_holdout_metrics")
    private Map<String, Object> confirmedHoldoutMetrics;

    @JsonProperty("fold_history")
    private List<Map<String, Object>> foldHistory;
}
