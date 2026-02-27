package org.application.mars.MarketData.models.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Branding {
    @JsonProperty("icon_url")
    private String iconUrl;

    @JsonProperty("logo_url")
    private String logoUrl;
}
