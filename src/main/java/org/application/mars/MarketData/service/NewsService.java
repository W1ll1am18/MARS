package org.application.mars.MarketData.service;

import lombok.RequiredArgsConstructor;
import org.application.mars.MarketData.client.MassiveClient;
import org.application.mars.MarketData.models.Massive.enums.Input.Order;
import org.application.mars.MarketData.models.Massive.enums.Input.SortNews;
import org.application.mars.MarketData.models.News.NewsResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class NewsService {
    private final MassiveClient massiveClient;

    //TODO more filters, Ticker, published_utc,
    public NewsResponse getNews(String ticker, String publishedUtc, Order order, Integer limit, SortNews sort) {
        StringBuilder url = new StringBuilder();

        if (ticker != null) {url.append("ticker=").append(ticker).append("&");}
        if (publishedUtc != null) {url.append("published_utc=").append(publishedUtc).append("&");}
        if (order != null) {url.append("order=").append(order).append("&");}
        if (limit != null) {url.append("limit=").append(limit).append("&");}
        if (sort != null) {url.append("sort=").append(sort.getValue()).append("&");}

        return massiveClient.getNews(url.toString());
    }
}
