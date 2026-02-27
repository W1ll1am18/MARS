package org.application.mars.MarketData.models.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IndicesGroups {
    private String cccy;
    private String cgi;
    @JsonProperty("dow_jones")
    private String dowJones;
    @JsonProperty("ftse_russell")
    private String ftseRussell;
    private String msci;
    private String mstarc;
    private String nasdaq;
    @JsonProperty("s_and_p")
    private String sAndP;
    @JsonProperty("societe_generale")
    private String societeGenerale;
}
