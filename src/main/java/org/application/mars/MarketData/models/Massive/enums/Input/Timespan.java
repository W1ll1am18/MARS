package org.application.mars.MarketData.models.Massive.enums.Input;

public enum Timespan {
    SECOND,
    MINUTE,
    HOUR,
    DAY,
    WEEK,
    MONTH,
    QUARTER,
    YEAR;

    public String getValue() {return this.toString().toLowerCase();}
}
