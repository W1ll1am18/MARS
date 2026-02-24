package org.application.mars.MarketData.models.Polygon.TechnicalIndicators;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.application.mars.MarketData.models.Objects.Underlying;
import org.application.mars.MarketData.models.Objects.Values;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SimpleMovingAverage {
    private Underlying underlying;
    private ArrayList<Values> values;
}
