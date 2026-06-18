package org.application.mars.MarketData.models.Massive.Operations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.application.mars.MarketData.models.Objects.Currencies;
import org.application.mars.MarketData.models.Objects.Exchanges;
import org.application.mars.MarketData.models.Objects.IndicesGroups;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarketStatusResponse {
    private boolean afterHours;
    private Currencies currencies;
    private boolean earlyHours;
    private Exchanges exchanges;
    private IndicesGroups indicesGroups;
    private String market;
    private String serverTime; //RFC3339 format
}
