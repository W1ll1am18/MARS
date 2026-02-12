package org.application.mars.MarketData.models.Polygon.Tickers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.application.mars.MarketData.models.Polygon.enums.Output.Locale;
import org.application.mars.MarketData.models.Polygon.enums.Output.Market;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticker {
    //Mandatory Responses
    private String ticker;
    private String name;
    private Market market;
    private Locale locale;

    //Optional Responses
    private Boolean active;

    @JsonProperty("base_currency_name")
    private String baseCurrencyName;

    @JsonProperty("base_currency_symbol")
    private String baseCurrencySymbol;

    private String cik;

    @JsonProperty("currency_name")
    private String currencyName;

    @JsonProperty("currency_symbol")
    private String currencySymbol;

    @JsonProperty("delisted_utc")
    private String delistedUtc;

    @JsonProperty("last_updated_utc")
    private String lastUpdatedUtc;

    @JsonProperty("primary_exchange")
    private String primaryExchange;

    @JsonProperty("share_class_figi")
    private String shareClassFigi;

    @JsonProperty("composite_figi")
    private String compositeFigi;

    private String type;
}
