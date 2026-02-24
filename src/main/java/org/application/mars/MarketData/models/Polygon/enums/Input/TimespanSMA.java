package org.application.mars.MarketData.models.Polygon.enums.Input;

public enum TimespanSMA {
    MINUTE,
    HOUR,
    DAY,
    WEEK,
    MONTH,
    QUARTER,
    YEAR;

    public String getValue() {return this.toString().toLowerCase();}
}
