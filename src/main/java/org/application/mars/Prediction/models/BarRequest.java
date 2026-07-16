package org.application.mars.Prediction.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BarRequest {
    @JsonProperty("ticker_id")
    private Long tickerId;
    @JsonProperty("trade_date")
    private String tradeDate;
    private BigDecimal open;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal close;
    long volume;
}
