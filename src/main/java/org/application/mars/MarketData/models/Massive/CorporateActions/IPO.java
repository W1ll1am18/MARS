package org.application.mars.MarketData.models.Massive.CorporateActions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.application.mars.MarketData.models.Massive.enums.Output.IPOStatus;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IPO {
    @JsonProperty("announced_date")
    private String announcedDate;

    @JsonProperty("currency_code")
    private String currencyCode;

    @JsonProperty("final_issue_price")
    private Double finalIssuePrice;

    @JsonProperty("highest_offer_price")
    private Double highestOfferPrice;

    @JsonProperty("ipo_status")
    private IPOStatus ipoStatus;

    @JsonProperty("isin")
    private String isin;

    @JsonProperty("issuer_name")
    private String issuerName;

    @JsonProperty("last_updated")
    private String lastUpdated;

    @JsonProperty("listing_date")
    private String listingDate;

    @JsonProperty("lot_size")
    private Long lotSize;

    @JsonProperty("lowest_offer_price")
    private Double lowestOfferPrice;

    @JsonProperty("max_shares_offered")
    private Long maxSharesOffered;

    @JsonProperty("min_shares_offered")
    private Long minSharesOffered;

    @JsonProperty("primary_exchange")
    private String primaryExchange;

    @JsonProperty("security_description")
    private String securityDescription;

    @JsonProperty("security_type")
    private String securityType;

    @JsonProperty("shares_outstanding")
    private Long sharesOutstanding;

    @JsonProperty("ticker")
    private String ticker;

    @JsonProperty("total_offer_size")
    private Double totalOfferSize;

    @JsonProperty("us_code")
    private String usCode;
}
