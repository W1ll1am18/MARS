package org.application.mars.MarketData.models.Polygon.Tickers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.application.mars.MarketData.models.Polygon.enums.AssetClass;
import org.application.mars.MarketData.models.Polygon.enums.Locale;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TickerType {
    @JsonProperty("asset_class")
    private AssetClass assetClass;

    private String code;

    private String description;

    private Locale locale;
}
