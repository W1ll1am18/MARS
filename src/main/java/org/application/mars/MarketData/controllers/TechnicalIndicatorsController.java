package org.application.mars.MarketData.controllers;

import lombok.RequiredArgsConstructor;
import org.application.mars.MarketData.models.Polygon.enums.Input.Order;
import org.application.mars.MarketData.models.Polygon.enums.Input.SeriesType;
import org.application.mars.MarketData.models.Polygon.enums.Input.TimespanSMA;
import org.application.mars.MarketData.service.TechnicalIndicatorsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/indicators")
public class TechnicalIndicatorsController {

    private final TechnicalIndicatorsService technicalIndicatorsService;

    @GetMapping("/sma/{stocksTicker}")
    public ResponseEntity<?> getSMA(
            @PathVariable String stocksTicker,
            @RequestParam (required = false) LocalDate timeStamp,
            @RequestParam (required = false) TimespanSMA timespan,
            @RequestParam (required = false, defaultValue = "true") Boolean adjusted,
            @RequestParam (required = false) Integer window,
            @RequestParam (required = false) SeriesType seriesType,
            @RequestParam (required = false) Boolean expandUnderlying,
            @RequestParam (required = false) Order order,
            @RequestParam (required = false) Integer limit
    ) {
        return ResponseEntity.ok(technicalIndicatorsService.getSimpleMovingAverage(stocksTicker,
                timeStamp, timespan, adjusted, window, seriesType, expandUnderlying, order, limit));
    }

}
