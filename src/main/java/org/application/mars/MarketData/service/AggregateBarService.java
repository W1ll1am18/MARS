package org.application.mars.MarketData.service;

import lombok.RequiredArgsConstructor;
import org.application.mars.MarketData.client.MassiveClient;
import org.application.mars.MarketData.models.Polygon.AggregateBars.CustomBarsResponse;
import org.application.mars.MarketData.models.Polygon.enums.Input.Order;
import org.application.mars.MarketData.models.Polygon.enums.Input.Timespan;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AggregateBarService {
    private final MassiveClient massiveClient;

    public CustomBarsResponse getCustomBars(String stocksTicker, Long multiplier, Timespan timeSpan, LocalDate from, LocalDate to, Boolean adjusted, Order order, Integer limit) {
        StringBuilder url = new StringBuilder();

        if (stocksTicker != null) {
            stocksTicker = stocksTicker.toUpperCase();
            url.append("ticker/").append(stocksTicker).append("/");
        }

        if (multiplier != null) { url.append("range/").append(multiplier).append("/");}
        if (timeSpan != null) { url.append(timeSpan.getValue()).append("/");}
        if (from != null) { url.append(from).append("/");}
        if (to != null) { url.append(to).append("?");}
        if (adjusted != null) { url.append("adjusted=").append(adjusted).append("&");}
        if (order != null) { url.append("sort=").append(order.getValue()).append("&");}
        if (limit != null) { url.append("limit=").append(limit).append("&");}

        return massiveClient.getCustomBars(url.toString());
    }
}
