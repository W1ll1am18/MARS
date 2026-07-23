package org.application.mars.MarketData.models.Finnhub.CompanyInfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sector {
    //Technically industries are not sectors but the responses im getting are sectors
    @JsonProperty("finnhubIndustry")
    private String sector;
}
