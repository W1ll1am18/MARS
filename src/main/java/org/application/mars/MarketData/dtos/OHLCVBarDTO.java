package org.application.mars.MarketData.dtos;

import lombok.Data;

@Data
public class OHLCVBarDTO {
    String time;
    double open;
    double high;
    double low;
    double close;
    long volume;
}
