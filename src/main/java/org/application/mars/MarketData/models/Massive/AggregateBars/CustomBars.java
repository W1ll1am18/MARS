package org.application.mars.MarketData.models.Massive.AggregateBars;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomBars {
    private BigDecimal c;
    private BigDecimal h;
    private BigDecimal l;
    private Long n;
    private BigDecimal o;
    private Boolean otc;
    private Long t;
    private Long v;
    private Long vw;
}
