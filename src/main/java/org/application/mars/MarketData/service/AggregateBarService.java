package org.application.mars.MarketData.service;

import lombok.RequiredArgsConstructor;
import org.application.mars.MarketData.client.MassiveClient;
import org.application.mars.MarketData.models.Massive.AggregateBars.DailyMarketSummaryResponse;
import org.application.mars.MarketData.models.Massive.AggregateBars.DailyTickerSummaryResponse;
import org.application.mars.MarketData.models.Massive.AggregateBars.PreviousDayBarResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AggregateBarService {
    private final MassiveClient massiveClient;

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
