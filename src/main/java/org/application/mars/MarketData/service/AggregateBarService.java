package org.application.mars.MarketData.service;

import lombok.RequiredArgsConstructor;
import org.application.mars.MarketData.client.MassiveClient;
import org.application.mars.MarketData.models.Massive.AggregateBars.CustomBarsResponse;
import org.application.mars.MarketData.models.Massive.AggregateBars.DailyMarketSummaryResponse;
import org.application.mars.MarketData.models.Massive.AggregateBars.DailyTickerSummaryResponse;
import org.application.mars.MarketData.models.Massive.AggregateBars.PreviousDayBarResponse;
import org.application.mars.MarketData.models.Massive.enums.Input.Order;
import org.application.mars.MarketData.models.Massive.enums.Input.Timespan;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AggregateBarService {
    private final MassiveClient massiveClient;

    public CustomBarsResponse getCustomBars(String stocksTicker, Long multiplier, Timespan timeSpan, LocalDate from, LocalDate to, Boolean adjusted, Order order, Integer limit) {
        StringBuilder url = new StringBuilder();

        url.append("ticker/").append(stocksTicker.toUpperCase()).append("/");
        url.append("range/").append(multiplier).append("/");
        url.append(timeSpan.getValue()).append("/");
        url.append(from).append("/");
        url.append(to).append("?");

        if (adjusted != null) {url.append("adjusted=").append(adjusted).append("&");}
        if (order != null) {url.append("sort=").append(order.getValue()).append("&");}
        if (limit != null) {url.append("limit=").append(limit).append("&");}

        return massiveClient.getCustomBars(url.toString());
    }

    public DailyMarketSummaryResponse getDailyMarketSummary(LocalDate date, Boolean adjusted, Boolean includeOtc) {
        StringBuilder url = new StringBuilder();

        url.append(date).append("?");
        if (adjusted != null) {url.append("adjusted=").append(adjusted).append("&");}
        if (includeOtc != null) {url.append("include_otc=").append(includeOtc).append("&");}

        return massiveClient.getDailyMarketSummary(url.toString());
    }

    public DailyTickerSummaryResponse getDailyTickerSummary(String stocksTicker, LocalDate date, Boolean adjusted) {
        StringBuilder url = new StringBuilder();

        url.append(stocksTicker).append("/");
        url.append(date).append("?");
        if (adjusted != null) {url.append("adjusted=").append(adjusted).append("&");}

        return massiveClient.getDailyTickerSummary(url.toString());
    }

    public PreviousDayBarResponse getPreviousDayBar(String stocksTicker, Boolean adjusted) {
        StringBuilder url = new StringBuilder();

        url.append(stocksTicker).append("/prev?");
        if (adjusted != null) {url.append("adjusted=").append(adjusted).append("&");}

        return massiveClient.getPreviousDayBar(url.toString());
    }
}
