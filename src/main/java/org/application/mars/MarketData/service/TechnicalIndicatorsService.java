package org.application.mars.MarketData.service;

import lombok.RequiredArgsConstructor;
import org.application.mars.MarketData.client.MassiveClient;
import org.application.mars.MarketData.models.Massive.Indicators.IndicatorResponse;
import org.application.mars.MarketData.models.Massive.enums.Indicator;
import org.application.mars.MarketData.models.Massive.enums.Input.Order;
import org.application.mars.MarketData.models.Massive.enums.Input.SeriesType;
import org.application.mars.MarketData.models.Massive.enums.Input.TimespanMA;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TechnicalIndicatorsService {
    private final MassiveClient massiveClient;

    public IndicatorResponse getIndicator(Indicator indicator, String stocksTicker, LocalDate timeStamp, TimespanMA timespan,
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

        if (indicator == Indicator.SMA) {return massiveClient.getSimpleMovingAverage(url.toString());}
        if (indicator == Indicator.EMA) {return massiveClient.getExponentialMovingAverage(url.toString());}
        if (indicator == Indicator.MACD) {return massiveClient.getMovingAverageConvergenceDivergence(url.toString());}
        if (indicator == Indicator.RSI) {return massiveClient.getRelativeStrengthIndex(url.toString());}

        return null;
    }
}
