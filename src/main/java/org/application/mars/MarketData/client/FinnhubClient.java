package org.application.mars.MarketData.client;

import lombok.RequiredArgsConstructor;
import org.application.mars.MarketData.models.Finnhub.CompanyInfo.BasicFinancialsResponse;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.Duration;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class FinnhubClient {

    @Value("${finnhub.api.key}")
    private String apiKey;
    private final WebClient webClient;

    private static final String BASE_URL = "https://finnhub.io/api/v1";

    private <T> T sendRequest(String url, Class<T> responseType) {
        log.info("Finnhub request: {}", url);
        try {
            return webClient.get().uri(url).retrieve().bodyToMono(responseType).block(Duration.ofSeconds(60));

        } catch (WebClientResponseException.TooManyRequests e) {
            log.error("Finnhub rate limit exceeded: {}", e.getResponseBodyAsString());
            throw new RuntimeException("Finnhub rate limit exceeded. Please try again later.");

        } catch (WebClientResponseException e) {
            log.error("Finnhub API error: {} - {}", e.getStatusCode(), e.getResponseBodyAsString());
            throw new RuntimeException("Failed to fetch from Finnhub: " + e.getMessage(), e);

        } catch (Exception e) {
            log.error("Error calling Finnhub API: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch from Finnhub.", e);
        }
    }

    private <T> T sendRequest(String url, ParameterizedTypeReference<T> responseType) {
        log.info("Finnhub request: {}", url);
        try {
            return webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(responseType)
                    .block(Duration.ofSeconds(60));

        } catch (WebClientResponseException.TooManyRequests e) {
            log.error("Finnhub rate limit exceeded: {}", e.getResponseBodyAsString());
            throw new RuntimeException("Finnhub rate limit exceeded. Please try again later.");

        } catch (WebClientResponseException e) {
            log.error("Finnhub API error: {} - {}", e.getStatusCode(), e.getResponseBodyAsString());
            throw new RuntimeException("Failed to fetch from Finnhub: " + e.getMessage(), e);

        } catch (Exception e) {
            log.error("Error calling Finnhub API: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch from Finnhub.", e);
        }
    }

    // /stock/metric?symbol=AAPL&metric=all
    // Returns P/E, P/B, 52-week high/low, margins, ROE, current ratio, beta, market cap etc.
    public BasicFinancialsResponse getBasicFinancials(String filtersUrl) {
        String url = BASE_URL + "/stock/metric?symbol=" + filtersUrl + "token=" + apiKey;
        return sendRequest(url, BasicFinancialsResponse.class);
    }

    public List<String> getPeers(String filtersUrl) {
        String url = BASE_URL + "/stock/peers?symbol=" + filtersUrl + "token=" + apiKey;
        return sendRequest(url, new ParameterizedTypeReference<List<String>>() {});
    }


    // /stock/profile2?symbol=AAPL
    // Returns IPO date, industry, market cap, shares outstanding, exchange, country
//    public CompanyProfileResponse getCompanyProfile(String symbol) {
//        String url = BASE_URL + "/stock/profile2?symbol=" + symbol + "&token=" + apiKey;
//        return sendRequest(url, CompanyProfileResponse.class);
//    }

    // /stock/financials-reported?symbol=AAPL&freq=annual
    // Returns raw income statement, balance sheet, cash flow (for computing EV/EBITDA, D/E etc.)
//    public FinancialsReportedResponse getFinancialsReported(String symbol, String freq) {
//        String url = BASE_URL + "/stock/financials-reported?symbol=" + symbol
//                + "&freq=" + freq + "&token=" + apiKey;
//        return sendRequest(url, FinancialsReportedResponse.class);
//    }

    // /company-news?symbol=AAPL&from=2024-01-01&to=2024-12-31
    // Returns news articles for a specific company
//    public List<CompanyNewsResponse> getCompanyNews(String symbol, String from, String to) {
//        String url = BASE_URL + "/company-news?symbol=" + symbol
//                + "&from=" + from + "&to=" + to + "&token=" + apiKey;
//        return sendRequest(url, new ParameterizedTypeReference<List<CompanyNewsResponse>>() {});
//        // news returns a JSON array not an object, so needs ParameterizedTypeReference
//    }

//    // separate sendRequest overload for list responses
//    private <T> T sendRequest(String url, ParameterizedTypeReference<T> responseType) {
//        try {
//            return webClient.get()
//                    .uri(url)
//                    .retrieve()
//                    .bodyToMono(responseType)
//                    .block(Duration.ofSeconds(60));
//        } catch (WebClientResponseException e) {
//            log.error("Finnhub API error: {} - {}", e.getStatusCode(), e.getResponseBodyAsString());
//            throw new RuntimeException("Failed to fetch from Finnhub: " + e.getMessage(), e);
//        } catch (Exception e) {
//            log.error("Error calling Finnhub API: {}", e.getMessage());
//            throw new RuntimeException("Failed to fetch from Finnhub.", e);
//        }
//    }
}
