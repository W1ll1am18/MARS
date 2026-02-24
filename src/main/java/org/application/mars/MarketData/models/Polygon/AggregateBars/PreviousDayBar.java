package org.application.mars.MarketData.models.Polygon.AggregateBars;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PreviousDayBar {
    private Double c;
    private Double h;
    private Double l;
    private Integer n;
    private Double o;
    private Long t;
    private Double v;
    private Double vw;
}
