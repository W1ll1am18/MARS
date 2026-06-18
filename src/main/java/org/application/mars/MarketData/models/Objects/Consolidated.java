package org.application.mars.MarketData.models.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Consolidated {
    @JsonProperty("updates_high_low")
    private Boolean updatesHighLow;
    @JsonProperty("updates_open_close")
    private Boolean updatesOpenClose;
    @JsonProperty("updates_volume")
    private Boolean updatesVolume;
}
