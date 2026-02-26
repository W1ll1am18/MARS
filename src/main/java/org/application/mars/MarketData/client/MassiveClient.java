package org.application.mars.MarketData.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.application.mars.MarketData.models.Massive.AggregateBars.CustomBarsResponse;
import org.application.mars.MarketData.models.Massive.AggregateBars.DailyMarketSummaryResponse;
import org.application.mars.MarketData.models.Massive.AggregateBars.DailyTickerSummaryResponse;
import org.application.mars.MarketData.models.Massive.AggregateBars.PreviousDayBarResponse;
import org.application.mars.MarketData.models.Massive.Indicators.IndicatorResponse;
import org.application.mars.MarketData.models.Massive.Operations.ExchangesResponse;
import org.application.mars.MarketData.models.Massive.Tickers.TickerOverviewResponse;
import org.application.mars.MarketData.models.Massive.Tickers.TickerRelatedResponse;
import org.application.mars.MarketData.models.Massive.Tickers.TickerResponse;
import org.application.mars.MarketData.models.Massive.Tickers.TickerTypeResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.Duration;

@Slf4j
@Component
@RequiredArgsConstructor
public class MassiveClient {
    @Value("${polygon.api.key}")
    private String apiKey;
    private final WebClient webClient;

    private <T> T sendRequest(String url, Class<T> responseType) {
        System.out.println(url);
        try {
            return webClient.get().uri(url).retrieve().bodyToMono(responseType).block(Duration.ofSeconds(10));

        } catch (WebClientResponseException.TooManyRequests e) {
            System.err.println("Rate limit exceeded" + e.getResponseBodyAsString());
            throw new RuntimeException("API rate limit exceeded. Please try again later");

        } catch (WebClientResponseException e) {
            System.err.println("API Error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            throw new RuntimeException("Failed to fetch: " + e.getMessage(), e);

        } catch (Exception e) {
            System.err.println("Error calling Polygon API: " + e.getMessage());
            throw new RuntimeException("Failed to fetch ", e);
        }
    }

    public TickerResponse getTickers(String filtersUrl) {
        String url = "https://api.massive.com/v3/reference/tickers?" + filtersUrl + "apiKey=" + apiKey;
        return sendRequest(url, TickerResponse.class);
    }

    public TickerTypeResponse getTickerTypes(String filtersUrl) {
        String url = "https://api.massive.com/v3/reference/tickers/types?" + filtersUrl + "apiKey=" + apiKey;
        return sendRequest(url, TickerTypeResponse.class);
    }

    public TickerOverviewResponse getTicker(String filtersUrl) {
        String url = "https://api.massive.com/v3/reference/tickers/" + filtersUrl + "apiKey=" + apiKey;
        return sendRequest(url, TickerOverviewResponse.class);
    }

    public TickerRelatedResponse getRelatedCompanies(String filtersUrl) {
        String url = "https://api.massive.com/v1/related-companies/" + filtersUrl + "apiKey=" + apiKey; //Idk why v1 works but v2 doesnt.
        return sendRequest(url, TickerRelatedResponse.class);
    }

    public CustomBarsResponse getCustomBars(String filtersUrl) {
        String url = "https://api.massive.com/v2/aggs/" + filtersUrl + "apiKey=" + apiKey;
        return sendRequest(url, CustomBarsResponse.class);
    }

    public DailyMarketSummaryResponse getDailyMarketSummary(String filtersUrl) {
        String url = "https://api.massive.com/v2/aggs/grouped/locale/us/market/stocks/" + filtersUrl + "apiKey=" +  apiKey;
        return sendRequest(url, DailyMarketSummaryResponse.class);
    }

    public DailyTickerSummaryResponse getDailyTickerSummary(String filtersUrl) {
        String url = "https://api.massive.com/v1/open-close/" + filtersUrl + "apiKey=" + apiKey;
        return sendRequest(url, DailyTickerSummaryResponse.class);
    }

    public PreviousDayBarResponse getPreviousDayBar(String filtersUrl) {
        String url = "https://api.massive.com/v2/aggs/ticker/" + filtersUrl + "apiKey=" + apiKey;
        return sendRequest(url, PreviousDayBarResponse.class);
    }

    public IndicatorResponse getSimpleMovingAverage(String filtersUrl) {
        String url = "https://api.massive.com/v1/indicators/sma/" + filtersUrl + "apiKey=" + apiKey;
        return sendRequest(url, IndicatorResponse.class);
    }

    public IndicatorResponse getExponentialMovingAverage(String filtersUrl) {
        String url = "https://api.massive.com/v1/indicators/ema/" + filtersUrl + "apiKey=" + apiKey;
        return sendRequest(url, IndicatorResponse.class);
    }

    public IndicatorResponse getMovingAverageConvergenceDivergence(String filtersUrl) {
        String url = "https://api.massive.com/v1/indicators/macd/" + filtersUrl + "apiKey=" + apiKey;
        return sendRequest(url, IndicatorResponse.class);
    }

    public IndicatorResponse getRelativeStrengthIndex(String filtersUrl) {
        String url = "https://api.massive.com/v1/indicators/rsi/" + filtersUrl + "apiKey=" + apiKey;
        return sendRequest(url, IndicatorResponse.class);
    }

    public ExchangesResponse getExchanges(String filtersUrl) {
        String url = "https://api.massive.com/v3/reference/exchanges?" +  filtersUrl + "apiKey=" + apiKey;
        return sendRequest(url, ExchangesResponse.class);
    }
}
