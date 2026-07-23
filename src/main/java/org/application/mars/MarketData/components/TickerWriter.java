package org.application.mars.MarketData.components;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.application.mars.MarketData.entities.TickerCompanyInfoEntity;
import org.application.mars.MarketData.entities.TickerEntity;
import org.application.mars.MarketData.entities.TickerFinancialsEntity;
import org.application.mars.MarketData.models.Finnhub.CompanyInfo.FinancialMetrics;
import org.application.mars.MarketData.models.Massive.Tickers.Ticker;
import org.application.mars.MarketData.models.Massive.Tickers.TickerOverview;
import org.application.mars.MarketData.models.Massive.enums.Locale;
import org.application.mars.MarketData.models.Massive.enums.Market;
import org.application.mars.MarketData.repository.TickerCompanyInfoRepository;
import org.application.mars.MarketData.repository.TickerFinancialsRepository;
import org.application.mars.MarketData.repository.TickerRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TickerWriter {
    private final TickerRepository tickerRepository;
    private final TickerCompanyInfoRepository tickerCompanyInfoRepository;
    private final TickerFinancialsRepository tickerFinancialsRepository;
    private final TickerSaver tickerSaver; // inject the new bean

    // NO @Transactional here, the method itself must not own a transaction
    // so that when TickerSaver's inner transaction fails and rolls back,
    // this method's context is completely clean for the retry
    public Optional<TickerEntity> upsertTicker(Ticker ticker) {
        if (isInvalid(ticker)) {
            return Optional.empty();
        }

        TickerEntity entity = buildEntity(ticker, findExisting(
                ticker.getCompositeFigi(),
                ticker.getTicker(),
                ticker.getPrimaryExchange(),
                ticker.getMarket(),
                ticker.getLocale()
        ));

        try {
            return tickerSaver.save(entity); // isolated transaction
        } catch (DataIntegrityViolationException e) {
            // TickerSaver's transaction rolled back completely
            TickerEntity existing = findExisting(
                    ticker.getCompositeFigi(),
                    ticker.getTicker(),
                    ticker.getPrimaryExchange(),
                    ticker.getMarket(),
                    ticker.getLocale()
            ).orElseThrow(() -> new IllegalStateException(
                    "Ticker missing after conflict: " + ticker.getTicker(), e));

            return tickerSaver.save(buildEntity(ticker, Optional.of(existing)));
        }
    }

    @Transactional
    public void upsertTickerOverview(TickerOverview overview) {
        Optional<TickerEntity> result = upsertTicker(overview);
        if (result.isEmpty()) {
            return;
        }

        TickerEntity tickerEntity = result.get();

        TickerCompanyInfoEntity infoEntity = tickerCompanyInfoRepository
                .findById(tickerEntity.getTickerId())
                .orElse(new TickerCompanyInfoEntity());

        infoEntity.setTicker(tickerEntity);
        infoEntity.setDescription(overview.getDescription());
        infoEntity.setHomepageUrl(overview.getHomepageUrl());
        infoEntity.setListDate(parseLocalDate(overview.getListDate()));
        infoEntity.setMarketCap(overview.getMarketCap());
        infoEntity.setPhoneNumber(overview.getPhoneNumber());
        infoEntity.setRoundLot(overview.getRoundLot());
        infoEntity.setShareClassSharesOutstanding(overview.getShareClassSharesOutstanding());
        infoEntity.setSicCode(overview.getSicCode());
        infoEntity.setSicDescription(overview.getSicDescription());
        infoEntity.setTickerRoot(overview.getTickerRoot());
        infoEntity.setTickerSuffix(overview.getTickerSuffix());
        infoEntity.setTotalEmployees(overview.getTotalEmployees());
        infoEntity.setWeightedSharesOutstanding(overview.getWeightedSharesOutstanding());
        infoEntity.setAccessed(Instant.now());

        tickerCompanyInfoRepository.save(infoEntity);
    }

    @Transactional
    public TickerFinancialsEntity upsertTickerFinancials(TickerEntity tickerEntity, FinancialMetrics metrics) {
        TickerFinancialsEntity entity = tickerFinancialsRepository
                .findById(tickerEntity.getTickerId())
                .orElse(new TickerFinancialsEntity());

        entity.setTicker(tickerEntity);

        // Beginner layer
        entity.setPeRatioTTM(metrics.getPeRatioTTM());
        entity.setPeRatioAnnual(metrics.getPeRatioAnnual());
        entity.setForwardPE(metrics.getForwardPE());
        entity.setPriceToBook(metrics.getPriceToBook());
        entity.setWeekHigh52(metrics.getWeekHigh52());
        entity.setWeekHigh52Date(metrics.getWeekHigh52Date());
        entity.setWeekLow52(metrics.getWeekLow52());
        entity.setWeekLow52Date(metrics.getWeekLow52Date());
        entity.setDividendYield(metrics.getDividendYield());
        entity.setDividendPerYear(metrics.getDividendPerYear());
        entity.setBeta(metrics.getBeta());
        entity.setEpsTTM(metrics.getEpsTTM());

        // Intermediate layer
        entity.setGrossMarginTTM(metrics.getGrossMarginTTM());
        entity.setGrossMarginAnnual(metrics.getGrossMarginAnnual());
        entity.setNetProfitMarginTTM(metrics.getNetProfitMarginTTM());
        entity.setNetProfitMarginAnnual(metrics.getNetProfitMarginAnnual());
        entity.setOperatingMarginTTM(metrics.getOperatingMarginTTM());
        entity.setReturnOnEquity(metrics.getReturnOnEquity());
        entity.setReturnOnAssets(metrics.getReturnOnAssets());
        entity.setReturnOnInvestment(metrics.getReturnOnInvestment());
        entity.setCurrentRatio(metrics.getCurrentRatio());
        entity.setQuickRatio(metrics.getQuickRatio());
        entity.setDebtToEquity(metrics.getDebtToEquity());
        entity.setPayoutRatio(metrics.getPayoutRatio());

        // Advanced layer
        entity.setEvEbitdaTTM(metrics.getEvEbitdaTTM());
        entity.setEvFreeCashFlow(metrics.getEvFreeCashFlow());
        entity.setEnterpriseValue(metrics.getEnterpriseValue());
        entity.setRevenueGrowthYoy(metrics.getRevenueGrowthYoy());
        entity.setRevenueGrowth3y(metrics.getRevenueGrowth3Year());
        entity.setRevenueGrowth5y(metrics.getRevenueGrowth5Year());
        entity.setEpsGrowthYoy(metrics.getEpsGrowthYoy());
        entity.setEpsGrowth3y(metrics.getEpsGrowth3Year());
        entity.setEpsGrowth5y(metrics.getEpsGrowth5Year());
        entity.setPegRatio(metrics.getPegRatio());
        entity.setForwardPegRatio(metrics.getForwardPegRatio());
        entity.setPriceToFreeCashFlow(metrics.getPriceToFreeCashFlow());

        entity.setAccessed(Instant.now());

        return tickerFinancialsRepository.save(entity);
    }


    private TickerEntity buildEntity(Ticker ticker, Optional<TickerEntity> existing) {
        TickerEntity entity = existing.orElse(new TickerEntity());
        entity.setCompositeFigi(ticker.getCompositeFigi());
        entity.setTicker(ticker.getTicker());
        entity.setName(ticker.getName());
        entity.setMarket(ticker.getMarket());
        entity.setLocale(ticker.getLocale());
        entity.setPrimaryExchange(ticker.getPrimaryExchange());
        entity.setActive(ticker.getActive());
        entity.setType(ticker.getType());
        entity.setCik(ticker.getCik());
        entity.setCurrencyName(ticker.getCurrencyName());
        entity.setCurrencySymbol(ticker.getCurrencySymbol());
        entity.setBaseCurrencyName(ticker.getBaseCurrencyName());
        entity.setBaseCurrencySymbol(ticker.getBaseCurrencySymbol());
        entity.setShareClassFigi(ticker.getShareClassFigi());
        entity.setDelistedUtc(parseInstant(ticker.getDelistedUtc()));
        entity.setLastUpdatedUtc(parseInstant(ticker.getLastUpdatedUtc()));
        entity.setAccessed(Instant.now());
        return entity;
    }

    private Optional<TickerEntity> findExisting(String compositeFigi, String ticker,
                                                String primaryExchange, Market market, Locale locale) {
        if (compositeFigi != null) {
            Optional<TickerEntity> byFigi = tickerRepository.findByCompositeFigi(compositeFigi);
            if (byFigi.isPresent()) return byFigi;
        }
        if (ticker != null && primaryExchange != null) {
            Optional<TickerEntity> byExchange = tickerRepository
                    .findByTickerAndPrimaryExchange(ticker, primaryExchange);
            if (byExchange.isPresent()) return byExchange;
        }
        if (market != null && locale != null) {
            return tickerRepository.findByTickerAndMarketAndLocale(ticker, market, locale);
        }
        return Optional.empty();
    }

    private Instant parseInstant(String isoTimestamp) {
        if (isoTimestamp == null || isoTimestamp.isBlank()) return null;
        try { return Instant.parse(isoTimestamp); }
        catch (DateTimeParseException e) { return null; }
    }

    private LocalDate parseLocalDate(String isoDate) {
        if (isoDate == null || isoDate.isBlank()) return null;
        try { return LocalDate.parse(isoDate); }
        catch (DateTimeParseException e) { return null; }
    }

    private boolean isInvalid(Ticker t) {
        return t.getName() == null || t.getTicker() == null
                || t.getName().isEmpty() || t.getTicker().isEmpty();
    }
}
