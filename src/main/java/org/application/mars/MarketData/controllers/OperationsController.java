package org.application.mars.MarketData.controllers;

import lombok.RequiredArgsConstructor;
import org.application.mars.MarketData.models.Massive.enums.AssetClassExchange;
import org.application.mars.MarketData.models.Massive.enums.Locale;
import org.application.mars.MarketData.service.OperationsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/marketOperations")
public class OperationsController {
    private final OperationsService operationsService;

    @GetMapping("/exchanges")
    public ResponseEntity<?> getExchanges(
            @RequestParam(required = false) AssetClassExchange assetClass,
            @RequestParam(required = false) Locale locale
    ){
        return ResponseEntity.ok(operationsService.getExchanges(assetClass, locale));
    }
}
