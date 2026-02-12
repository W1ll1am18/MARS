package org.application.mars.MarketData.service;

import lombok.RequiredArgsConstructor;
import org.application.mars.MarketData.client.PolygonClient;
import org.application.mars.MarketData.models.Polygon.Tickers.TickerResponse;
import org.application.mars.MarketData.models.Polygon.enums.Input.Market;
import org.application.mars.MarketData.models.Polygon.enums.Input.Order;
import org.application.mars.MarketData.models.Polygon.enums.Input.Sort;
import org.application.mars.MarketData.models.Polygon.enums.Input.Type;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class TickerService {
    private final PolygonClient polygonClient;

    private void validateString(String input, String fieldName) {
        if (input == null) return;
        if (!input.matches("[A-Za-z0-9\\.\\- ]*")) {
            throw new IllegalArgumentException("Invalid characters in field '" + fieldName + "': " + input);
        }
    }

    public TickerResponse getTickers(String ticker, Type type, Market market, String exchange, String cusip, String cik,
                                     String date, String search, Boolean active, Order order, Integer limit, Sort sort){

        validateString(ticker, "ticker");
        validateString(exchange, "exchange");
        validateString(cusip, "cusip");
        validateString(cik, "cik");
        validateString(date, "date");
        validateString(search, "search");

        StringBuilder url = new StringBuilder();

        if (ticker != null) url.append("ticker=").append(URLEncoder.encode(ticker, StandardCharsets.UTF_8));
        if (exchange != null) url.append("&exchange=").append(URLEncoder.encode(exchange, StandardCharsets.UTF_8));
        if (cusip != null) url.append("&cusip=").append(URLEncoder.encode(cusip, StandardCharsets.UTF_8));
        if (cik != null) url.append("&cik=").append(URLEncoder.encode(cik, StandardCharsets.UTF_8));
        if (date != null) url.append("&date=").append(URLEncoder.encode(date, StandardCharsets.UTF_8));
        if (search != null) url.append("&search=").append(URLEncoder.encode(search, StandardCharsets.UTF_8));
        if (type != null) url.append("&type=").append(type.getValue());
        if (market != null) url.append("&market=").append(market.getValue());
        if (order != null) url.append("&order=").append(order.getValue());
        if (sort != null) url.append("&sort=").append(sort.getValue());
        if (active != null) url.append("&active=").append(active);
        if (limit != null) url.append("&limit=").append(limit);

        return polygonClient.getTickers(url.toString());
    }
}
