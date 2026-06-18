package org.application.mars.MarketData.models.Massive.Indicators;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.application.mars.MarketData.models.Objects.Underlying;
import org.application.mars.MarketData.models.Objects.Values;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Indicator {
    private Underlying underlying;
    private ArrayList<Values> values;
}
