package org.application.mars.MarketData.models.Massive.CorporateActions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.application.mars.MarketData.models.Objects.TickerEvent;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TickerEvents {
    private ArrayList<TickerEvent> events;
    private String name;
}
