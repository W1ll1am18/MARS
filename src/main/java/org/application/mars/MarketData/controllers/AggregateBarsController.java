package org.application.mars.MarketData.controllers;

import lombok.RequiredArgsConstructor;
import org.application.mars.MarketData.models.Polygon.enums.Input.Order;
import org.application.mars.MarketData.models.Polygon.enums.Input.Timespan;
import org.application.mars.MarketData.service.AggregateBarService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bars")
public class AggregateBarsController {
    private final AggregateBarService aggregateBarService;

    @GetMapping("/custom/{stocksTicker}/{multiplier}/{timeSpan}/{from}/{to}")
    public ResponseEntity<?> getCustomBars(
            @PathVariable String stocksTicker,
            @PathVariable Long multiplier,
            @PathVariable Timespan timeSpan,
            @PathVariable LocalDate from, //YYYY-MM-DD
            @PathVariable LocalDate to, //YYYY-MM-DD
            @RequestParam(required = false, defaultValue = "true") Boolean adjusted,
            @RequestParam(required = false, defaultValue = "ASC") Order order,
            @RequestParam(required = false, defaultValue = "5000") Integer limit
    ) {
        return ResponseEntity.ok(aggregateBarService.getCustomBars(stocksTicker, multiplier, timeSpan, from, to, adjusted, order, limit));
    }
}
