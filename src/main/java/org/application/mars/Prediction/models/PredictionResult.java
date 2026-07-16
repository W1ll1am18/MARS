package org.application.mars.Prediction.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PredictionResult {
    @JsonProperty("ticker_id")
    private Integer tickerId;

    @JsonProperty("predicted_class")
    private Integer predictedClass;

    @JsonProperty("prob_down")
    private Double probDown;

    @JsonProperty("prob_timeout")
    private Double probTimeout;

    @JsonProperty("prob_up")
    private Double probUp;

    @JsonProperty("in_trained_scope")
    private Boolean inTrainedScope;

    @JsonProperty("suitability_note")
    private String suitabilityNote;
}
