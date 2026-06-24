package org.application.mars.MarketData.service;

import lombok.RequiredArgsConstructor;
import org.application.mars.MarketData.dtos.TickerOverviewDTO;
import org.application.mars.MarketData.models.Finnhub.CompanyInfo.BasicFinancialsResponse;
import org.application.mars.MarketData.models.Finnhub.CompanyInfo.FinancialMetrics;
import org.application.mars.MarketData.models.Massive.Tickers.TickerOverview;
import org.application.mars.MarketData.models.Massive.Tickers.TickerOverviewResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TickerOverviewService {
    private final TickerService tickerService;
    private final CompanyMetricsService companyMetricsService;

    public TickerOverviewDTO getTickerOverview (String ticker, LocalDate date) {
        TickerOverview massiveData = tickerService.getTicker(ticker, date);
        FinancialMetrics finnhubData = companyMetricsService.getCompanyMetrics(ticker);
        return merge(massiveData, finnhubData);
    }

    private TickerOverviewDTO merge(TickerOverview massiveData, FinancialMetrics finnhubData) {
        TickerOverviewDTO mergedDTO = new TickerOverviewDTO();

        // MASSIVE fields
        mergedDTO.setTicker(massiveData.getTicker());
        mergedDTO.setName(massiveData.getName());
        mergedDTO.setMarket(massiveData.getMarket().getName());
        mergedDTO.setLocale(massiveData.getLocale().getValue());
        mergedDTO.setPrimaryExchange(massiveData.getPrimaryExchange());
        mergedDTO.setCompositeFigi(massiveData.getCompositeFigi());
        mergedDTO.setDescription(massiveData.getDescription());
        mergedDTO.setHomepageUrl(massiveData.getHomepageUrl());
        mergedDTO.setListDate(massiveData.getListDate());
        mergedDTO.setTotalEmployees(massiveData.getTotalEmployees());
        mergedDTO.setMarketCap(massiveData.getMarketCap());
        mergedDTO.setSicCode(massiveData.getSicCode());
        mergedDTO.setSicDescription(massiveData.getSicDescription());

        // Finnhub fields
        mergedDTO.setPeRatioTTM(finnhubData.getPeRatioTTM());
        mergedDTO.setForwardPE(finnhubData.getForwardPE());
        mergedDTO.setPriceToBook(finnhubData.getPriceToBook());
        mergedDTO.setBeta(finnhubData.getBeta());
        mergedDTO.setWeekHigh52(finnhubData.getWeekHigh52());
        mergedDTO.setWeekHigh52Date(finnhubData.getWeekHigh52Date());
        mergedDTO.setWeekLow52(finnhubData.getWeekLow52());
        mergedDTO.setWeekLow52Date(finnhubData.getWeekLow52Date());
        mergedDTO.setDividendYield(finnhubData.getDividendYield());
        mergedDTO.setEpsTTM(finnhubData.getEpsTTM());
        mergedDTO.setGrossMarginTTM(finnhubData.getGrossMarginTTM());
        mergedDTO.setNetProfitMarginTTM(finnhubData.getNetProfitMarginTTM());
        mergedDTO.setOperatingMarginTTM(finnhubData.getOperatingMarginTTM());
        mergedDTO.setReturnOnEquity(finnhubData.getReturnOnEquity());
        mergedDTO.setCurrentRatio(finnhubData.getCurrentRatio());
        mergedDTO.setDebtToEquity(finnhubData.getDebtToEquity());
        mergedDTO.setEvEbitdaTTM(finnhubData.getEvEbitdaTTM());
        mergedDTO.setRevenueGrowthYoy(finnhubData.getRevenueGrowthYoy());
        mergedDTO.setPegRatio(finnhubData.getPegRatio());

        return mergedDTO;
    }
}
