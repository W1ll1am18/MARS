package org.application.mars.MarketData.service;

import lombok.RequiredArgsConstructor;
import org.application.mars.MarketData.client.MassiveClient;
import org.application.mars.MarketData.models.Massive.Fundamentals.FloatResponse;
import org.application.mars.MarketData.models.Massive.Fundamentals.ShortInterestResponse;
import org.application.mars.MarketData.models.Massive.Fundamentals.ShortVolumeResponse;
import org.application.mars.MarketData.models.Massive.enums.Input.Order;
import org.application.mars.MarketData.models.Massive.enums.Input.SortFloat;
import org.application.mars.MarketData.models.Massive.enums.Input.SortShortInterest;
import org.application.mars.MarketData.models.Massive.enums.Input.SortShortVolume;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class FundamentalsService {
    private final MassiveClient massiveClient;

    //TODO ticker, daysToCover, settlementDate and AvgDaily volume have more filters
    public ShortInterestResponse getShortResponse(String ticker, Integer daysToCover, LocalDate settlementDate,
                                                  Integer avgDailyVolume, Integer limit, SortShortInterest column, Order order) {
        StringBuilder url = new StringBuilder();

        if (ticker != null) {url.append("ticker=").append(ticker).append("&");}
        if (daysToCover != null) {url.append("days=").append(daysToCover).append("&");}
        if (settlementDate != null) {url.append("settlement_date=").append(settlementDate).append("&");}
        if (avgDailyVolume != null) {url.append("avg_daily_volume=").append(avgDailyVolume).append("&");}
        if (limit != null) {url.append("limit=").append(limit).append("&");}
        if (column != null && order != null) {url.append("sort=").append(column.getValue()).append(".").append(order.getValue()).append("&");}

        return massiveClient.getShortInterestResponse(url.toString());
    }

    //TODO ticker, date and shortvolumen Ratio have more filters
    public ShortVolumeResponse shortVolumeResponse(String ticker, LocalDate date, Double shortVolumeRatio, Integer limit, SortShortVolume column, Order order) {
        StringBuilder url = new StringBuilder();
        if (ticker != null) {url.append("ticker=").append(ticker).append("&");}
        if (date != null) {url.append("date=").append(date).append("&");}
        if (shortVolumeRatio != null) {url.append("short_volume_ratio=").append(shortVolumeRatio).append("&");}
        if (limit != null) {url.append("limit=").append(limit).append("&");}
        if (column != null && order != null) {url.append("sort=").append(column.getValue()).append(".").append(order.getValue()).append("&");}

        return massiveClient.getShortVolumeResponse(url.toString());
    }

    //TODO ticker, free float percent have more filters
    public FloatResponse getFloatResponse(String ticker, Double freeFloatPercent, Integer limit, SortFloat column, Order order) {
        StringBuilder url = new StringBuilder();
        if (ticker != null) {url.append("ticker=").append(ticker).append("&");}
        if (freeFloatPercent != null) {url.append("free_float_percent=").append(freeFloatPercent).append("&");}
        if (limit != null) {url.append("limit=").append(limit).append("&");}
        if (column != null && order != null) {url.append("sort=").append(column.getValue()).append(".").append(order.getValue()).append("&");}

        return massiveClient.getFloatResponse(url.toString());
    }
}
