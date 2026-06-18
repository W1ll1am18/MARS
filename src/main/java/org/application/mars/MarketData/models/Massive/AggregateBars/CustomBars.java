package org.application.mars.MarketData.models.Massive.AggregateBars;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomBars {
    private Long c;
    private Long h;
    private Long l;
    private Long n;
    private Long o;
    private Long otc;
    private Long t;
    private Long v;
    private Long vw;
}
