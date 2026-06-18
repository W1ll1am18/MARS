package org.application.mars.MarketData.models.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.application.mars.MarketData.models.Massive.enums.Output.Sentiment;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Insights {
    @JsonProperty("sentiment")
    private Sentiment sentiment;

    @JsonProperty("sentiment_reasoning")
    private String sentimentReasoning;

    @JsonProperty("ticker")
    private String ticker;

}
