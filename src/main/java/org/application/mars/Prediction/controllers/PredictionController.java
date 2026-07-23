package org.application.mars.Prediction.controllers;

import lombok.RequiredArgsConstructor;
import org.application.mars.Prediction.models.PredictionResponse;
import org.application.mars.Prediction.service.PredictionsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PredictionController {
    private final PredictionsService predictionsService;

    @GetMapping("/predict/{ticker}/{horizon}")
    public ResponseEntity<PredictionResponse> getPrediction(
            @PathVariable String ticker,
            @PathVariable Integer horizon
    ) {
        return ResponseEntity.ok(predictionsService.predict(ticker, horizon));
    }
}
