package org.application.mars.MarketData.dtos;

import lombok.Data;

import java.util.List;

@Data
public class OHLCVDTO {
    List<OHLCVBarDTO> results;
}
