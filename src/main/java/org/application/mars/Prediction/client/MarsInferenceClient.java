package org.application.mars.Prediction.client;

import lombok.extern.slf4j.Slf4j;
import org.application.mars.Prediction.models.PredictionResponse;
import org.application.mars.Prediction.models.PredictionRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
@Component
public class MarsInferenceClient {

    @Value("${mars.api.key}")
    private String apiKey;
    private final WebClient webClient;

    public MarsInferenceClient(@Value("${mars.inference.url}") String baseUrl) {
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    public PredictionResponse predict(PredictionRequest request) {
        log.info("MARS inference request: horizon={}, tickers={}", request.getHorizon(), request.getBars().size());
        try {
            return webClient.post()
                .uri("/predict")
                .header("X-API-Key", apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response ->
                    response.bodyToMono(String.class).flatMap(body -> {
                        log.error("MARS inference validation error: {}", body);
                        return Mono.error(new RuntimeException("MARS validation error: " + body));
                    })
                )
                .bodyToMono(PredictionResponse.class)
                .block(Duration.ofSeconds(60));
        } catch (Exception e) {
            log.error("Error calling MARS inference API: {}", e.getMessage());
            throw new RuntimeException("Failed to get prediction from MARS.", e);
        }
    }
}
