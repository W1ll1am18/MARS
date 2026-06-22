package org.application.mars.MarketData.controllers;

import lombok.RequiredArgsConstructor;
import org.application.mars.MarketData.service.CompanyMetricsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.util.repeat.RepeatSpec;

@RestController
@RequiredArgsConstructor
@RequestMapping("/metrics")
public class CompanyMetricsController {
    private final CompanyMetricsService companyMetricsService;

    @GetMapping("/statistics/{ticker}")
    public ResponseEntity<?> getCompanyMetrics (
        @PathVariable String ticker
    ) {
        return ResponseEntity.ok(companyMetricsService.getCompanyMetrics(ticker));
    }
}
