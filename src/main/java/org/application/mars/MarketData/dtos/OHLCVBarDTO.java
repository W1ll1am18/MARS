package org.application.mars.MarketData.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OHLCVBarDTO {
    String time;
    BigDecimal open;
    BigDecimal high;
    BigDecimal low;
    BigDecimal close;
    long volume;
}
