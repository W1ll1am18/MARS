package org.application.mars.MarketData.models.Disclosures;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Index {
    @JsonProperty("accession_number")
    private String accessionNumber;

    @JsonProperty("cik")
    private String cik;

    @JsonProperty("filing_date")
    private String filingDate;

    @JsonProperty("filing_url")
    private String filingUrl;

    @JsonProperty("form_type")
    private String formType;

    @JsonProperty("issuer_name")
    private String issuerName;

    @JsonProperty("ticker")
    private String ticker;
}
