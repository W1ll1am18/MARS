package org.application.mars.MarketData.models.Massive.Operations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.application.mars.MarketData.models.Massive.enums.Output.AssetClassCondition;
import org.application.mars.MarketData.models.Massive.enums.Output.TypeConditionCode;
import org.application.mars.MarketData.models.Objects.SipMapping;
import org.application.mars.MarketData.models.Objects.UpdateRules;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConditionCodes {
    private String abbreviation;
    @JsonProperty("asset_class")
    private AssetClassCondition assetClass;
    @JsonProperty("data_types")
    private ArrayList<String> dataTypes;
    private String description;
    private Long exchange;
    private Integer id;
    private Boolean legacy;
    private String name;
    @JsonProperty("sip_mapping")
    private SipMapping sipMapping;
    private TypeConditionCode type;
    @JsonProperty("update_rules")
    private UpdateRules updateRules;
}
