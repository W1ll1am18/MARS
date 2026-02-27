package org.application.mars.MarketData.models.Massive.enums.Input;

public enum Sip {
    CTA,
    UTP,
    OPRA;

    public String getValue() {
        return this.name().toLowerCase();
    }
}
