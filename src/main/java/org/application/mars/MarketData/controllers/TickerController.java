package org.application.mars.MarketData.controllers;

import lombok.RequiredArgsConstructor;
import org.application.mars.MarketData.models.Polygon.Tickers.TickerResponse;
import org.application.mars.MarketData.models.Polygon.enums.Input.Market;
import org.application.mars.MarketData.models.Polygon.enums.Input.Order;
import org.application.mars.MarketData.models.Polygon.enums.Input.Sort;
import org.application.mars.MarketData.models.Polygon.enums.Input.Type;
import org.application.mars.MarketData.service.TickerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stocks")
public class TickerController {
    private final TickerService tickerService;

    @GetMapping("/tickers")
    public ResponseEntity<?> getTickers(
            @RequestParam(required = false) String ticker,
            @RequestParam(required = false) Type type,
            @RequestParam(required = false) Market market,
            @RequestParam(required = false) String exchange,
            @RequestParam(required = false) String cusip,
            @RequestParam(required = false) String cik,
            @RequestParam(required = false) String date,
            @RequestParam(required = false) String search,
            @RequestParam(required = false, defaultValue = "true") Boolean active,
            @RequestParam(required = false, defaultValue = "ASC") Order order,
            @RequestParam(required = false, defaultValue = "100") Integer limit, //Max is 1000
            @RequestParam(required = false) Sort sort
    ) {
        return ResponseEntity.ok(tickerService.getTickers(ticker, type, market, exchange, cusip, cik, date, search, active, order, limit, sort));
    }
}
