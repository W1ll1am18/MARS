package org.application.mars.MarketData.controllers;

import lombok.RequiredArgsConstructor;
import org.application.mars.MarketData.models.Massive.enums.Indicator;
import org.application.mars.MarketData.models.Massive.enums.Input.Order;
import org.application.mars.MarketData.models.Massive.enums.Input.SeriesType;
import org.application.mars.MarketData.models.Massive.enums.Input.TimespanMA;
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
            @RequestParam (required = false) TimespanMA timespan,
            @RequestParam (required = false, defaultValue = "true") Boolean adjusted,
            @RequestParam (required = false) Integer window,
            @RequestParam (required = false) SeriesType seriesType,
            @RequestParam (required = false) Boolean expandUnderlying,
            @RequestParam (required = false) Order order,
            @RequestParam (required = false) Integer limit
    ) {
        Indicator indicator = Indicator.SMA;
        return ResponseEntity.ok(technicalIndicatorsService.getIndicator(indicator, stocksTicker,
                timeStamp, timespan, adjusted, window, seriesType, expandUnderlying, order, limit));
    }

    @GetMapping("/ema/{stocksTicker}")
    public ResponseEntity<?> getEMA(
            @PathVariable String stocksTicker,
            @RequestParam (required = false) LocalDate timeStamp,
            @RequestParam (required = false) TimespanMA timespan,
            @RequestParam (required = false, defaultValue = "true") Boolean adjusted,
            @RequestParam (required = false) Integer window,
            @RequestParam (required = false) SeriesType seriesType,
            @RequestParam (required = false) Boolean expandUnderlying,
            @RequestParam (required = false) Order order,
            @RequestParam (required = false) Integer limit
    ) {
        Indicator indicator = Indicator.EMA;
        return ResponseEntity.ok(technicalIndicatorsService.getIndicator(indicator, stocksTicker,
                timeStamp, timespan, adjusted, window, seriesType, expandUnderlying, order, limit));
    }

    @GetMapping("/macd/{stocksTicker}")
    public ResponseEntity<?> getMACD(
            @PathVariable String stocksTicker,
            @RequestParam (required = false) LocalDate timeStamp,
            @RequestParam (required = false) TimespanMA timespan,
            @RequestParam (required = false, defaultValue = "true") Boolean adjusted,
            @RequestParam (required = false) Integer window,
            @RequestParam (required = false) SeriesType seriesType,
            @RequestParam (required = false) Boolean expandUnderlying,
            @RequestParam (required = false) Order order,
            @RequestParam (required = false) Integer limit
    ) {
        Indicator indicator = Indicator.MACD;
        return ResponseEntity.ok(technicalIndicatorsService.getIndicator(indicator, stocksTicker,
                timeStamp, timespan, adjusted, window, seriesType, expandUnderlying, order, limit));
    }

    @GetMapping("/rsi/{stocksTicker}")
    public ResponseEntity<?> getRSI(
            @PathVariable String stocksTicker,
            @RequestParam (required = false) LocalDate timeStamp,
            @RequestParam (required = false) TimespanMA timespan,
            @RequestParam (required = false, defaultValue = "true") Boolean adjusted,
            @RequestParam (required = false) Integer window,
            @RequestParam (required = false) SeriesType seriesType,
            @RequestParam (required = false) Boolean expandUnderlying,
            @RequestParam (required = false) Order order,
            @RequestParam (required = false) Integer limit
    ) {
        Indicator indicator = Indicator.RSI;
        return ResponseEntity.ok(technicalIndicatorsService.getIndicator(indicator, stocksTicker,
                timeStamp, timespan, adjusted, window, seriesType, expandUnderlying, order, limit));
    }

}
