package org.application.mars.MarketData.service;

import lombok.RequiredArgsConstructor;
import org.application.mars.MarketData.client.MassiveClient;
import org.application.mars.MarketData.models.Polygon.Tickers.TickerOverviewResponse;
import org.application.mars.MarketData.models.Polygon.Tickers.TickerRelatedResponse;
import org.application.mars.MarketData.models.Polygon.Tickers.TickerResponse;
import org.application.mars.MarketData.models.Polygon.Tickers.TickerTypeResponse;
import org.application.mars.MarketData.models.Polygon.enums.Input.Market;
import org.application.mars.MarketData.models.Polygon.enums.Input.Order;
import org.application.mars.MarketData.models.Polygon.enums.Input.Sort;
import org.application.mars.MarketData.models.Polygon.enums.Input.Type;
import org.application.mars.MarketData.models.Polygon.enums.Locale;
import org.application.mars.MarketData.models.Polygon.enums.AssetClass;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TickerService {
    private final MassiveClient massiveClient;

    public TickerResponse getTickers(String ticker, Type type, Market market, String exchange, String cusip, String cik,
                                     LocalDate date, String search, Boolean active, Order order, Integer limit, Sort sort){

        //Care Invalid inputs.
        StringBuilder url = new StringBuilder();

        if (ticker != null) {
            ticker = ticker.toUpperCase();
            url.append("ticker=").append(URLEncoder.encode(ticker, StandardCharsets.UTF_8)).append("&");
        }

        if (exchange != null) {
            exchange = exchange.toUpperCase();
            url.append("exchange=").append(URLEncoder.encode(exchange, StandardCharsets.UTF_8)).append("&");
        }

        if (search != null) url.append("search=").append(URLEncoder.encode(search, StandardCharsets.UTF_8)).append("&"); //Varied
        if (cusip != null) url.append("cusip=").append(URLEncoder.encode(cusip, StandardCharsets.UTF_8)).append("&"); //Numbers but string
        if (cik != null) url.append("cik=").append(URLEncoder.encode(cik, StandardCharsets.UTF_8)).append("&"); //Numbers but string
        if (market != null) url.append("market=").append(market.getValue()).append("&"); //Lowercase
        if (order != null) url.append("order=").append(order.getValue()).append("&"); //Lowercase
        if (sort != null) url.append("sort=").append(sort.getValue()).append("&"); //Lowercase
        if (type != null) url.append("type=").append(type.getValue()).append("&"); //Uppercase
        if (active != null) url.append("active=").append(active).append("&"); //Lowercase
        if (limit != null) url.append("limit=").append(limit).append("&"); //Lowercase
        if (date != null) url.append("date=").append(date).append("&");

        return massiveClient.getTickers(url.toString());
    }

    public TickerTypeResponse getTickerTypes(AssetClass assetClass, Locale locale) {
        StringBuilder url = new StringBuilder();

        if (assetClass != null) {url.append("asset_class=").append(assetClass.getValue()).append("&");} //Lowercase
        if (locale != null) {url.append("locale=").append(locale.getValue()).append("&");} //Lowercase

        return massiveClient.getTickerTypes(url.toString());
    }

    public TickerOverviewResponse getTicker(String ticker, LocalDate date) {
        StringBuilder url = new StringBuilder();

        url.append(ticker.toUpperCase()).append("?");
        if (date != null) {url.append("date=").append(date).append("&");}

        return massiveClient.getTicker(url.toString());
    }

    public TickerRelatedResponse getRelatedCompanies(String ticker) {
        StringBuilder url = new StringBuilder();

        url.append(ticker.toUpperCase()).append("?");
        return massiveClient.getRelatedCompanies(url.toString());
    }

}
