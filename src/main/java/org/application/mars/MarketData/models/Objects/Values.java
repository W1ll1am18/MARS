package org.application.mars.MarketData.models.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Values {
    private Double histogram; //The difference between the MACD line (value) and the signal line (signal). Positive histogram values indicate upward (bullish) momentum, while negative histogram values indicate downward (bearish) momentum.
    private Double signal; //The signal line value, calculated as the exponential moving average (EMA) of the MACD line (value) over the signal period defined in the request parameters. Traders typically use crossovers between the MACD and signal lines as trading signals.
    private Long timestamp; //The Unix Msec timestamp from the last aggregate used in this calculation.
    private Double value; //The MACD line value, calculated as the difference between the short-term and long-term exponential moving averages (EMAs) based on the periods specified in the request parameters.
}
