package org.application.mars.Prediction.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PredictionResult {
    @JsonAlias("ticker_id")
    private Integer tickerId;

    @JsonAlias("predicted_class")
    private Integer predictedClass;

    @JsonAlias("prob_down")
    private Double probDown;

    @JsonAlias("prob_timeout")
    private Double probTimeout;

    @JsonAlias("prob_up")
    private Double probUp;

    @JsonAlias("scope")
    private String scope;

    @JsonAlias("suitability_note")
    private String suitabilityNote;
}
