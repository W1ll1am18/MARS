package org.application.mars.MarketData.controllers;

import lombok.RequiredArgsConstructor;
import org.application.mars.MarketData.models.Massive.enums.Input.Order;
import org.application.mars.MarketData.models.Massive.enums.Input.SortNews;
import org.application.mars.MarketData.service.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/news")
public class NewsController {
    private final NewsService newsService;

    @RequestMapping("/sentiment")
    public ResponseEntity<?> getNews(
            @RequestParam(required = false) String ticker,
            @RequestParam(required = false) String publishedUtc, //Input can either be of format: YYYY-MM-DD OR YYYY-MM-DD T HH:MM:SS Z
            @RequestParam(required = false) Order order,
            @RequestParam(required = false, defaultValue = "10") Integer limit,
            @RequestParam(required = false) SortNews sort
    ) {
        return ResponseEntity.ok(newsService.getNews(ticker, publishedUtc, order, limit, sort));
    }
}
