package org.application.mars.MarketData.controllers;

import lombok.RequiredArgsConstructor;
import org.application.mars.MarketData.models.Massive.enums.Input.Order;
import org.application.mars.MarketData.models.Massive.enums.Input.Timespan;
import org.application.mars.MarketData.service.AggregateBarService;
import org.application.mars.MarketData.service.PriceChartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bars")
public class AggregateBarsController {
    private final AggregateBarService aggregateBarService;
    private final PriceChartService priceChartService;

//    @GetMapping("/custom/{stocksTicker}/{multiplier}/{timeSpan}/{from}/{to}")
//    public ResponseEntity<?> getCustomBars(
//            @PathVariable String stocksTicker,
//            @PathVariable Long multiplier,
//            @PathVariable Timespan timeSpan,
//            @PathVariable LocalDate from, //YYYY-MM-DD
//            @PathVariable LocalDate to, //YYYY-MM-DD
//            @RequestParam(required = false, defaultValue = "true") Boolean adjusted,
//            @RequestParam(required = false, defaultValue = "ASC") Order order,
//            @RequestParam(required = false, defaultValue = "5000") Integer limit
//    ) {
//        return ResponseEntity.ok(aggregateBarService.getCustomBars(stocksTicker, multiplier, timeSpan, from, to, adjusted, order, limit));
//    }

    @GetMapping("/custom/{stocksTicker}")
    public ResponseEntity<?> getCustomBars(
            @PathVariable String stocksTicker,
            @RequestParam(defaultValue = "1") Long multiplier,
            @RequestParam(defaultValue = "DAY") Timespan timeSpan,
            @RequestParam(required = false) LocalDate from,
            @RequestParam(required = false) LocalDate to,
            @RequestParam(required = false, defaultValue = "true") Boolean adjusted,
            @RequestParam(required = false, defaultValue = "ASC") Order order,
            @RequestParam(required = false) Integer limit
    ) {
        //From, to and limit are passed in as null care. Service handles this
        return ResponseEntity.ok(priceChartService.getPrices(stocksTicker, multiplier, timeSpan, from, to, adjusted, order, limit));
    }

    @GetMapping("/daily/{date}")
    public ResponseEntity<?> getDailyMarketSummary (
            @PathVariable LocalDate date,
            @RequestParam(required = false, defaultValue = "true") Boolean adjusted,
            @RequestParam(required = false, defaultValue = "true") Boolean includeOtc
    ) {
        return ResponseEntity.ok(aggregateBarService.getDailyMarketSummary(date, adjusted, includeOtc));
    }

    @GetMapping("/daily/{ticker}/{date}")
    public ResponseEntity<?> getDailyTickerSummary (
            @PathVariable String ticker,
            @PathVariable LocalDate date,
            @RequestParam(required = false, defaultValue = "true") Boolean adjusted
    ) {
        return ResponseEntity.ok(aggregateBarService.getDailyTickerSummary(ticker, date, adjusted));
    }

    @GetMapping("/prev/{ticker}")
    public ResponseEntity<?> getPreviousDayBar (
            @PathVariable String ticker,
            @RequestParam(required = false, defaultValue = "true") Boolean adjusted
    ) {
        return ResponseEntity.ok(aggregateBarService.getPreviousDayBar(ticker, adjusted));
    }
}
