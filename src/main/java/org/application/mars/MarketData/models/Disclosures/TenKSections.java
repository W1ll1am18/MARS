package org.application.mars.MarketData.models.Disclosures;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TenKSections {
    @JsonProperty("cik")
    private String cik;

    @JsonProperty("filing_date")
    private String filingDate;

    @JsonProperty("filing_url")
    private String filingUrl;

    @JsonProperty("period_end")
    private String periodEnd;

    @JsonProperty("section")
    private String section;

    @JsonProperty("text")
    private String text;

    @JsonProperty("ticker")
    private String ticker;
}
