package org.application.mars.MarketData.models.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Exchanges {
    private String nasdaq;
    private String nyse;
    private String otc;
}
