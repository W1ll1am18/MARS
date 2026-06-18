package org.application.mars.MarketData.models.Massive.Operations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.application.mars.MarketData.models.Massive.enums.AssetClassExchange;
import org.application.mars.MarketData.models.Massive.enums.Locale;
import org.application.mars.MarketData.models.Massive.enums.Output.TypeExchange;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Exchanges {
    private String acronym;
    @JsonProperty("asset_class")
    private AssetClassExchange assetClass;
    private Integer id;
    private Locale locale;
    private String mic;
    private String name;
    @JsonProperty("operating_mic")
    private String operatingMic;
    @JsonProperty("participant_id")
    private String participantId;
    private TypeExchange type;
    private String url;
}
