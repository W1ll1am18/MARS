package org.application.mars.MarketData.service;

import lombok.RequiredArgsConstructor;
import org.application.mars.MarketData.client.MassiveClient;
import org.application.mars.MarketData.models.Polygon.TechnicalIndicators.SimpleMovingAverageResponse;
import org.application.mars.MarketData.models.Polygon.enums.Input.Order;
import org.application.mars.MarketData.models.Polygon.enums.Input.SeriesType;
import org.application.mars.MarketData.models.Polygon.enums.Input.Timespan;
import org.application.mars.MarketData.models.Polygon.enums.Input.TimespanSMA;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TechnicalIndicatorsService {
    private final MassiveClient massiveClient;

    public SimpleMovingAverageResponse getSimpleMovingAverage(String stocksTicker, LocalDate timeStamp, TimespanSMA timespan,
                                                              Boolean adjusted, Integer window, SeriesType seriesType,
                                                              Boolean expandUnderlying, Order order, Integer limit)
    {
        StringBuilder url = new StringBuilder();
        url.append(stocksTicker).append("?");
        if (timeStamp != null) {url.append("timeStamp=").append(adjusted).append("&");}
        if (timespan != null) {url.append("timespan=").append(timespan.getValue()).append("&");}
        if (adjusted != null) {url.append("adjusted=").append(adjusted).append("&");}
        if (window != null) {url.append("window=").append(window).append("&");}
        if (seriesType != null) {url.append("series_type=").append(seriesType).append("&");}
        if (expandUnderlying != null) {url.append("expand_underlying=").append(expandUnderlying).append("&");}
        if (order != null) {url.append("order=").append(order).append("&");}
        if (limit != null) {url.append("limit=").append(limit).append("&");}

        return massiveClient.getSimpleMovingAverage(url.toString());
    }
}
