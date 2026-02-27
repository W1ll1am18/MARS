package org.application.mars.MarketData.controllers;

import lombok.RequiredArgsConstructor;
import org.application.mars.MarketData.models.Massive.enums.AssetClassExchange;
import org.application.mars.MarketData.models.Massive.enums.Input.DataType;
import org.application.mars.MarketData.models.Massive.enums.Input.Order;
import org.application.mars.MarketData.models.Massive.enums.Input.Sip;
import org.application.mars.MarketData.models.Massive.enums.Input.SortConditionCodes;
import org.application.mars.MarketData.models.Massive.enums.Locale;
import org.application.mars.MarketData.models.Massive.enums.Output.AssetClassCondition;
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

    @GetMapping("/holidays")
    public ResponseEntity<?> getHolidays() {
        return ResponseEntity.ok(operationsService.getMarketHolidays());
    }

    @GetMapping("/status")
    public ResponseEntity<?> getMarketStatus() {
        return ResponseEntity.ok(operationsService.getMarketStatus());
    }

    @GetMapping("/codes")
    public ResponseEntity<?> getMarketCodes(
            @RequestParam(required = false) AssetClassCondition assetClass,
            @RequestParam(required = false) DataType dataType,
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) Sip sip,
            @RequestParam(required = false) Order order,
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false) SortConditionCodes sort
    ) {
        return ResponseEntity.ok(operationsService.getConditionCodes(assetClass, dataType, id, sip, order, limit, sort));
    }
}
