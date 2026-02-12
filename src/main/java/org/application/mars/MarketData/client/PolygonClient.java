package org.application.mars.MarketData.client;

import lombok.RequiredArgsConstructor;
import org.application.mars.MarketData.models.Polygon.Tickers.TickerResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@Component
@RequiredArgsConstructor
public class PolygonClient {
    @Value("${polygon.api.key}")
    private String apiKey;
    private final WebClient webClient;

    public TickerResponse getTickers(String filtersUrl) {
        String url = "https://api.massive.com/v3/reference/tickers?" + filtersUrl + "&apiKey=" + apiKey;
        System.out.println(url);

        return webClient.get().uri(url).retrieve().bodyToMono(TickerResponse.class).block();
    }
}
