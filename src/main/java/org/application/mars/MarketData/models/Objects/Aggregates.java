package org.application.mars.MarketData.models.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Aggregates {
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
