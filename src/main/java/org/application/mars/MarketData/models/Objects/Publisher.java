package org.application.mars.MarketData.models.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Publisher {
    @JsonProperty("favicon_url")
    private String faviconUrl;

    @JsonProperty("homepage_url")
    private String homepageUrl;

    @JsonProperty("logo_url")
    private String logoUrl;

    @JsonProperty("name")
    private String name;
}
