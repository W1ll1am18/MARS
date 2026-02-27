package org.application.mars.MarketData.controllers;

import lombok.RequiredArgsConstructor;
import org.application.mars.MarketData.models.Massive.enums.Input.Market;
import org.application.mars.MarketData.models.Massive.enums.Input.Order;
import org.application.mars.MarketData.models.Massive.enums.Input.Sort;
import org.application.mars.MarketData.models.Massive.enums.Input.Type;
import org.application.mars.MarketData.models.Massive.enums.AssetClass;
import org.application.mars.MarketData.models.Massive.enums.Locale;
import org.application.mars.MarketData.service.TickerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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
            @RequestParam(required = false) LocalDate date, //YYYY-MM-DD
            @RequestParam(required = false) String search,
            @RequestParam(required = false, defaultValue = "true") Boolean active,
            @RequestParam(required = false, defaultValue = "ASC") Order order,
            @RequestParam(required = false, defaultValue = "100") Integer limit, //Max is 1000
            @RequestParam(required = false) Sort sort
    ) {
        return ResponseEntity.ok(tickerService.getTickers(ticker, type, market, exchange, cusip, cik, date, search, active, order, limit, sort));
    }

    @GetMapping("/tickers/types")
    public ResponseEntity<?> getTickerTypes(
            @RequestParam(name = "asset_class", required = false) AssetClass assetClass,
            @RequestParam(required = false) Locale locale
    ) {
        return ResponseEntity.ok(tickerService.getTickerTypes(assetClass, locale));
    }

    @GetMapping("/tickers/{ticker}")
    public ResponseEntity<?> getTicker(
            @PathVariable String ticker,
            @RequestParam(required = false) LocalDate date
    ) {
        return ResponseEntity.ok(tickerService.getTicker(ticker, date));
    }

    @GetMapping("/related-companies/{ticker}")
    public ResponseEntity<?> getRelatedCompanies(
            @PathVariable String ticker
    ) {
        return ResponseEntity.ok(tickerService.getRelatedCompanies(ticker));
    }
}
