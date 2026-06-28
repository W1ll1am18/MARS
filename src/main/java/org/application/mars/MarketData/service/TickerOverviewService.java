package org.application.mars.MarketData.service;

import lombok.RequiredArgsConstructor;
import org.application.mars.MarketData.dtos.TickerOverviewDTO;
import org.application.mars.MarketData.entities.TickerEntity;
import org.application.mars.MarketData.models.Finnhub.CompanyInfo.FinancialMetrics;
import org.application.mars.MarketData.models.Massive.Tickers.TickerOverview;
import org.application.mars.MarketData.repository.TickerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TickerOverviewService {
    private final TickerService tickerService;
    private final TickerMetricsService tickerMetricsService;
    private final TickerRepository tickerRepository;

    public TickerOverviewDTO getTickerOverview (String ticker, LocalDate date) {
        TickerOverview massiveData = tickerService.getTicker(ticker, date);
        TickerEntity tickerEntity = tickerRepository.findByTicker(ticker.toUpperCase())
                .orElseThrow(() -> new IllegalStateException("Ticker missing after overview fetch"));
        FinancialMetrics finnhubData = tickerMetricsService.getTickerMetrics(ticker, tickerEntity);
        return merge(massiveData, finnhubData, tickerEntity);
    }

    private TickerOverviewDTO merge(TickerOverview massiveData, FinancialMetrics finnhubData, TickerEntity tickerEntity) {
        TickerOverviewDTO mergedDTO = new TickerOverviewDTO();

        // ── MASSIVE fields ────────────────────────────────────────
        mergedDTO.setType(tickerEntity.getType());

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
        mergedDTO.setPhoneNumber(massiveData.getPhoneNumber());
        mergedDTO.setRoundLot(massiveData.getRoundLot());
        mergedDTO.setTickerRoot(massiveData.getTickerRoot());
        mergedDTO.setTickerSuffix(massiveData.getTickerSuffix());
        mergedDTO.setShareClassSharesOutstanding(massiveData.getShareClassSharesOutstanding());
        mergedDTO.setWeightedSharesOutstanding(massiveData.getWeightedSharesOutstanding());

        // ── Finnhub fields ────────────────────────────────────────
        if (finnhubData == null) return mergedDTO; // Finnhub may be unavailable

        // Valuation
        mergedDTO.setPeRatioTTM(finnhubData.getPeRatioTTM());
        mergedDTO.setPeRatioAnnual(finnhubData.getPeRatioAnnual());
        mergedDTO.setForwardPE(finnhubData.getForwardPE());
        mergedDTO.setPriceToBook(finnhubData.getPriceToBook());
        mergedDTO.setPegRatio(finnhubData.getPegRatio());
        mergedDTO.setForwardPegRatio(finnhubData.getForwardPegRatio());
        mergedDTO.setEvEbitdaTTM(finnhubData.getEvEbitdaTTM());
        mergedDTO.setEvFreeCashFlow(finnhubData.getEvFreeCashFlow());
        mergedDTO.setPriceToFreeCashFlow(finnhubData.getPriceToFreeCashFlow());
        mergedDTO.setEnterpriseValue(finnhubData.getEnterpriseValue());
        mergedDTO.setDividendYield(finnhubData.getDividendYield());
        mergedDTO.setDividendPerYear(finnhubData.getDividendPerYear());

        // Price metrics
        mergedDTO.setWeekHigh52(finnhubData.getWeekHigh52());
        mergedDTO.setWeekHigh52Date(finnhubData.getWeekHigh52Date());
        mergedDTO.setWeekLow52(finnhubData.getWeekLow52());
        mergedDTO.setWeekLow52Date(finnhubData.getWeekLow52Date());
        mergedDTO.setBeta(finnhubData.getBeta());
        mergedDTO.setEpsTTM(finnhubData.getEpsTTM());
        mergedDTO.setEpsGrowthYoy(finnhubData.getEpsGrowthYoy());

        // Profitability
        mergedDTO.setGrossMarginTTM(finnhubData.getGrossMarginTTM());
        mergedDTO.setGrossMarginAnnual(finnhubData.getGrossMarginAnnual());
        mergedDTO.setOperatingMarginTTM(finnhubData.getOperatingMarginTTM());
        mergedDTO.setNetProfitMarginTTM(finnhubData.getNetProfitMarginTTM());
        mergedDTO.setNetProfitMarginAnnual(finnhubData.getNetProfitMarginAnnual());
        mergedDTO.setReturnOnEquity(finnhubData.getReturnOnEquity());
        mergedDTO.setReturnOnAssets(finnhubData.getReturnOnAssets());
        mergedDTO.setReturnOnInvestment(finnhubData.getReturnOnInvestment());
        mergedDTO.setPayoutRatio(finnhubData.getPayoutRatio());

        // Financial health
        mergedDTO.setCurrentRatio(finnhubData.getCurrentRatio());
        mergedDTO.setQuickRatio(finnhubData.getQuickRatio());
        mergedDTO.setDebtToEquity(finnhubData.getDebtToEquity());

        // Growth
        mergedDTO.setRevenueGrowthYoy(finnhubData.getRevenueGrowthYoy());
        mergedDTO.setRevenueGrowth3y(finnhubData.getRevenueGrowth3Year());
        mergedDTO.setRevenueGrowth5y(finnhubData.getRevenueGrowth5Year());
        mergedDTO.setEpsGrowthYoy(finnhubData.getEpsGrowthYoy());
        mergedDTO.setEpsGrowth3y(finnhubData.getEpsGrowth3Year());
        mergedDTO.setEpsGrowth5y(finnhubData.getEpsGrowth5Year());

        return mergedDTO;
    }
}
