package org.application.mars.MarketData.controllers;

import jdk.jfr.Experimental;
import lombok.RequiredArgsConstructor;
import org.application.mars.MarketData.models.Massive.enums.Input.*;
import org.application.mars.MarketData.service.CorporateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/actions")
public class CorporateController {
    private final CorporateService corporateService;

    @GetMapping("/ipos")
    public ResponseEntity<?> getIPOs(
            @RequestParam(required = false) String ticker,
            @RequestParam(required = false) String usCode,
            @RequestParam(required = false) String isin,
            @RequestParam(required = false) LocalDate listingDate,
            @RequestParam(required = false) IPOStatus ipoStatus,
            @RequestParam(required = false) Order order,
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false) SortIPO sort
    ) {
        return ResponseEntity.ok(corporateService.getIPOResponse(ticker, usCode, isin, listingDate, ipoStatus, order, limit, sort));
    }

    @GetMapping("/splits")
    public ResponseEntity<?> getSplits(
            @RequestParam(required = false) String ticker,
            @RequestParam(required = false) LocalDate executionDate,
            @RequestParam(required = false) AdjustmentType adjustmentType,
            @RequestParam(required = false, defaultValue = "100") Integer limit,
            @RequestParam(required = false, defaultValue = "EXECUTION_DATE") SortSplits column,
            @RequestParam(required = false, defaultValue = "DESC") Order order
    ) {
        return ResponseEntity.ok(corporateService.getSplits(ticker, executionDate, adjustmentType, limit, column, order));
    }

    @GetMapping("/dividends")
    public ResponseEntity<?> getDividends(
            @RequestParam(required = false) String ticker,
            @RequestParam(required = false) LocalDate exDividendDate,
            @RequestParam(required = false) Integer frequency,
            @RequestParam(required = false) DistributionType distributionType,
            @RequestParam(required = false, defaultValue = "100") Integer limit,
            @RequestParam(required = false, defaultValue = "TICKER") SortDividend column,
            @RequestParam(required = false, defaultValue = "ASC") Order order
    ) {
        return ResponseEntity.ok(corporateService.getDividends(ticker, exDividendDate, frequency, distributionType, limit, column, order));
    }


    @GetMapping("/tickerEvents/{id}")
    public ResponseEntity<?> getTickerEvents (
            @PathVariable String id,
            @RequestParam(required = false) TypeTickerEvent types
    ) {
        return ResponseEntity.ok(corporateService.getTickerEvents(id, types));
    }

}
