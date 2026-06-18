package org.application.mars.MarketData.models.Disclosures;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RiskCategories {
    @JsonProperty("description")
    private String description;

    @JsonProperty("primary_category")
    private String primaryCategory;

    @JsonProperty("secondary_category")
    private String secondaryCategory;

    @JsonProperty("taxonomy")
    private Double taxonomy;

    @JsonProperty("tertiary_category")
    private String tertiaryCategory;
}
