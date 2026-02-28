package org.application.mars.MarketData.models.Disclosures;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RiskFactors {
    @JsonProperty("cik")
    private String cik;

    @JsonProperty("filing_date")
    private String filingDate;

    @JsonProperty("primary_category")
    private String primaryCategory;

    @JsonProperty("secondary_category")
    private String secondaryCategory;

    @JsonProperty("supporting_text")
    private String supportingText;

    @JsonProperty("tertiary_category")
    private String tertiaryCategory;

    @JsonProperty("ticker")
    private String ticker;
}
