package org.application.mars.MarketData.models.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TickerEvent {
    private String date;
    @JsonProperty("event_type")
    private String eventType;
    @JsonProperty("ticker_change")
    private TickerChange tickerChange;
}
