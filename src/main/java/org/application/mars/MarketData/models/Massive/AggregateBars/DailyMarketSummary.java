package org.application.mars.MarketData.models.Massive.AggregateBars;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyMarketSummary {
    private String T;
    private Double c;
    private Double h;
    private Double l;
    private Integer n;
    private Double o;
    private Boolean otc;
    private Long t;
    private Double v;
    private Double vw;
}
