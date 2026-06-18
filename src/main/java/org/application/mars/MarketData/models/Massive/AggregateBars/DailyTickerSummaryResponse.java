package org.application.mars.MarketData.models.Massive.AggregateBars;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyTickerSummaryResponse {
    private Double afterHours;
    private Double close;
    private String from;
    private Double high;
    private Double low;
    private Double open;
    private Boolean otc;
    private Double preMarket;
    private String status;
    private String symbol;
    private Long volume;

}
