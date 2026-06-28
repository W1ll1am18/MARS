package org.application.mars.MarketData.service;

import lombok.RequiredArgsConstructor;
import org.application.mars.Common.utils.Constant;
import org.application.mars.MarketData.client.FinnhubClient;
import org.application.mars.MarketData.components.TickerWriter;
import org.application.mars.MarketData.entities.TickerEntity;
import org.application.mars.MarketData.entities.TickerFinancialsEntity;
import org.application.mars.MarketData.models.Finnhub.CompanyInfo.BasicFinancialsResponse;
import org.application.mars.MarketData.models.Finnhub.CompanyInfo.FinancialMetrics;
import org.application.mars.MarketData.repository.TickerFinancialsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TickerMetricsService {
    private final FinnhubClient finnhubClient;
    private final TickerFinancialsRepository tickerFinancialsRepository;
    private final TickerWriter tickerWriter;

    public FinancialMetrics getTickerMetrics(String ticker, TickerEntity tickerEntity) {
        if (!isEquity(tickerEntity)) { return null; } // merge() already handles null

        Optional<TickerFinancialsEntity> cachedMetrics = tickerFinancialsRepository.findByTicker(ticker.toUpperCase());

        //May not need to refresh after 24 hrs
        boolean tickerFresh = !Constant.isStale(tickerEntity.getAccessed());
        boolean metricFresh = cachedMetrics.isPresent() && !Constant.isStale(cachedMetrics.get().getAccessed());

        if (tickerFresh && metricFresh) {
            return mapToFinancials(cachedMetrics.get());
        }

        String url = ticker + "&metric=all&";
        BasicFinancialsResponse apiResponse = finnhubClient.getBasicFinancials(url);
        FinancialMetrics response = apiResponse != null ? apiResponse.getMetric() : null;

        if (response == null) {
            throw new NullPointerException("Metric response is null");
        }

        tickerWriter.upsertTickerFinancials(tickerEntity, response);
        return response;
    }

    private FinancialMetrics mapToFinancials(TickerFinancialsEntity e) {
        FinancialMetrics f = new FinancialMetrics();

        // Valuation
        f.setPeRatioTTM(e.getPeRatioTTM());
        f.setPeRatioAnnual(e.getPeRatioAnnual());
        f.setForwardPE(e.getForwardPE());
        f.setPriceToBook(e.getPriceToBook());
        f.setPegRatio(e.getPegRatio());
        f.setForwardPegRatio(e.getForwardPegRatio());
        f.setEvEbitdaTTM(e.getEvEbitdaTTM());
        f.setEvFreeCashFlow(e.getEvFreeCashFlow());
        f.setPriceToFreeCashFlow(e.getPriceToFreeCashFlow());
        f.setEnterpriseValue(e.getEnterpriseValue());
        f.setDividendYield(e.getDividendYield());
        f.setDividendPerYear(e.getDividendPerYear());

        // Price metrics
        f.setWeekHigh52(e.getWeekHigh52());
        f.setWeekHigh52Date(e.getWeekHigh52Date());
        f.setWeekLow52(e.getWeekLow52());
        f.setWeekLow52Date(e.getWeekLow52Date());
        f.setBeta(e.getBeta());
        f.setEpsTTM(e.getEpsTTM());

        // Profitability
        f.setGrossMarginTTM(e.getGrossMarginTTM());
        f.setGrossMarginAnnual(e.getGrossMarginAnnual());
        f.setOperatingMarginTTM(e.getOperatingMarginTTM());
        f.setNetProfitMarginTTM(e.getNetProfitMarginTTM());
        f.setNetProfitMarginAnnual(e.getNetProfitMarginAnnual());
        f.setReturnOnEquity(e.getReturnOnEquity());
        f.setReturnOnAssets(e.getReturnOnAssets());
        f.setReturnOnInvestment(e.getReturnOnInvestment());
        f.setPayoutRatio(e.getPayoutRatio());

        // Financial health
        f.setCurrentRatio(e.getCurrentRatio());
        f.setQuickRatio(e.getQuickRatio());
        f.setDebtToEquity(e.getDebtToEquity());

        // Growth
        f.setRevenueGrowthYoy(e.getRevenueGrowthYoy());
        f.setRevenueGrowth3Year(e.getRevenueGrowth3y());
        f.setRevenueGrowth5Year(e.getRevenueGrowth5y());
        f.setEpsGrowthYoy(e.getEpsGrowthYoy());
        f.setEpsGrowth3Year(e.getEpsGrowth3y());
        f.setEpsGrowth5Year(e.getEpsGrowth5y());

        return f;
    }

    // Only fetch metrics for equity types. Finnhub returns nothing useful for ETFs, indices, etc.
    private boolean isEquity(TickerEntity tickerEntity) {
        String type = tickerEntity.getType();
        return type == null || type.equals("CS") || type.equals("ADRC") || type.equals("ADRP") || type.equals("ADRR");
    }
}
