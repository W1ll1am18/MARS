package org.application.mars.MarketData.models.Massive.Operations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarketHolidaysResponse {
    private String close;
    private String date;
    private String exchange;
    private String name;
    private String open;
    private String status;
}
