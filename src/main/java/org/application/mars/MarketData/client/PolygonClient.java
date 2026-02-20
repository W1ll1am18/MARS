package org.application.mars.MarketData.client;

import lombok.RequiredArgsConstructor;
import org.application.mars.MarketData.models.Polygon.Tickers.TickerOverviewResponse;
import org.application.mars.MarketData.models.Polygon.Tickers.TickerRelatedResponse;
import org.application.mars.MarketData.models.Polygon.Tickers.TickerResponse;
import org.application.mars.MarketData.models.Polygon.Tickers.TickerTypeResponse;
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
        String url = "https://api.massive.com/v3/reference/tickers?" + filtersUrl + "apiKey=" + apiKey;
        System.out.println(url);

        return webClient.get().uri(url).retrieve().bodyToMono(TickerResponse.class).block();
    }

    public TickerTypeResponse getTickerTypes(String filtersUrl) {
        String url = "https://api.massive.com/v3/reference/tickers/types?" + filtersUrl + "apiKey=" + apiKey;
        System.out.println(url);

        return webClient.get().uri(url).retrieve().bodyToMono(TickerTypeResponse.class).block();
    }

    public TickerOverviewResponse getTicker(String filtersUrl) {
        String url = "https://api.massive.com/v3/reference/tickers/" + filtersUrl + "apiKey=" + apiKey;
        System.out.println(url);

        return webClient.get().uri(url).retrieve().bodyToMono(TickerOverviewResponse.class).block();
    }

    public TickerRelatedResponse getRelatedCompanies(String filtersUrl) {
        String url = "https://api.massive.com/v1/related-companies/" + filtersUrl + "apiKey=" + apiKey; //Idk why v1 works but v2 doesnt. Care
        System.out.println(url);

        return webClient.get().uri(url).retrieve().bodyToMono(TickerRelatedResponse.class).block();
    }
}
