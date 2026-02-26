package org.application.mars.MarketData.models.Massive.Tickers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.application.mars.MarketData.models.Objects.Address;
import org.application.mars.MarketData.models.Objects.Branding;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TickerOverview extends Ticker {

    private Address address;
    private Branding branding;

    @JsonProperty("list_date")
    private String listDate;

    private String description;

    @JsonProperty("homepage_url")
    private String homepageUrl;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("round_lot")
    private Integer roundLot;

    @JsonProperty("share_class_shares_outstanding")
    private Long shareClassSharesOutstanding;

    @JsonProperty("sic_code")
    private String sicCode;

    @JsonProperty("sic_description")
    private String sicDescription;

    @JsonProperty("ticker_root")
    private String tickerRoot;

    @JsonProperty("ticker_suffix")
    private String tickerSuffix;

    @JsonProperty("total_employees")
    private Long totalEmployees;

    @JsonProperty("weighted_shares_outstanding")
    private Long weightedSharesOutstanding;

    @JsonProperty("market_cap")
    private Long marketCap;
}