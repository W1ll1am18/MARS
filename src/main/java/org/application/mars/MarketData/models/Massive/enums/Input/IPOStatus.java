package org.application.mars.MarketData.models.Massive.enums.Input;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum IPOStatus {
    DIRECT_LISTING_PROCESS,
    HISTORY,
    NEW,
    PENDING,
    POSTPONED,
    RUMOR,
    WITHDRAWN;

    public String getValue () {
        return this.name().toLowerCase();
    }
}
