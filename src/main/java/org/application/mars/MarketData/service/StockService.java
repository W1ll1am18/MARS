package org.application.mars.MarketData.service;

import lombok.AllArgsConstructor;
import org.application.mars.MarketData.models.YahooFinance.StockWrapper;
import org.springframework.stereotype.Service;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class StockService {
    public StockWrapper findStock(final String ticker){ //Symbol of stock
        try {
            return new StockWrapper(YahooFinance.get(ticker));
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public BigDecimal findPrice(final StockWrapper stock) throws IOException {
        boolean refresh = true; //Can adjust later
        return stock.getStock().getQuote(true).getPrice();
    }
}
