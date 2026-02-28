package org.application.mars.MarketData.controllers;

import lombok.RequiredArgsConstructor;
import org.application.mars.MarketData.models.Massive.enums.Input.Order;
import org.application.mars.MarketData.models.Massive.enums.Input.SortFloat;
import org.application.mars.MarketData.models.Massive.enums.Input.SortShortInterest;
import org.application.mars.MarketData.models.Massive.enums.Input.SortShortVolume;
import org.application.mars.MarketData.service.FundamentalsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fundamentals")
public class FundamentalsController {
    private final FundamentalsService fundamentalsService;

    @GetMapping("/interest")
    public ResponseEntity<?> getInterest(
            @RequestParam(required = false) String ticker,
            @RequestParam(required = false) Integer daysToCover,
            @RequestParam(required = false) LocalDate settlementDate,
            @RequestParam(required = false) Integer avgDailyVolume,
            @RequestParam(required = false, defaultValue = "10") Integer limit,
            @RequestParam(required = false, defaultValue = "TICKER") SortShortInterest column,
            @RequestParam(required = false, defaultValue = "ASC") Order order
    ) {
        return ResponseEntity.ok(fundamentalsService.getShortResponse(ticker, daysToCover, settlementDate, avgDailyVolume, limit, column, order));
    }

    @GetMapping("/volume")
    public ResponseEntity<?> getVolume(
            @RequestParam(required = false) String ticker,
            @RequestParam(required = false) LocalDate date,
            @RequestParam(required = false) Double shortVolumeRatio,
            @RequestParam(required = false, defaultValue = "10") Integer limit,
            @RequestParam(required = false, defaultValue = "TICKER") SortShortVolume column,
            @RequestParam(required = false, defaultValue = "ASC") Order order
    ) {
        return ResponseEntity.ok(fundamentalsService.shortVolumeResponse(ticker, date, shortVolumeRatio, limit, column, order));
    }

    @GetMapping("/float")
    public ResponseEntity<?> getFloat(
            @RequestParam(required = false) String ticker,
            @RequestParam(required = false) Double freeFloatPercent,
            @RequestParam(required = false, defaultValue = "100") Integer limit,
            @RequestParam(required = false, defaultValue = "TICKER") SortFloat column,
            @RequestParam(required = false, defaultValue = "ASC") Order order
    ) {
        return ResponseEntity.ok(fundamentalsService.getFloatResponse(ticker, freeFloatPercent, limit, column, order));
    }
}
