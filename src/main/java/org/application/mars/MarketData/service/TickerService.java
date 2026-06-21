package org.application.mars.MarketData.service;

import lombok.RequiredArgsConstructor;
import org.application.mars.MarketData.client.MassiveClient;
import org.application.mars.MarketData.entities.TickerCompanyInfoEntity;
import org.application.mars.MarketData.entities.TickerEntity;
import org.application.mars.MarketData.models.Massive.Tickers.*;
import org.application.mars.MarketData.models.Massive.enums.Market;
import org.application.mars.MarketData.models.Massive.enums.Input.Order;
import org.application.mars.MarketData.models.Massive.enums.Input.Sort;
import org.application.mars.MarketData.models.Massive.enums.Input.Type;
import org.application.mars.MarketData.models.Massive.enums.Locale;
import org.application.mars.MarketData.models.Massive.enums.AssetClass;
import org.application.mars.MarketData.repository.TickerCompanyInfoRepository;
import org.application.mars.MarketData.repository.TickerRepository;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TickerService {
    private final MassiveClient massiveClient;
    private final TickerRepository tickerRepository;
    private final TickerCompanyInfoRepository tickerCompanyInfoRepository;
    private static final Duration TICKER_TTL = Duration.ofHours(24);

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
        if (market != null) url.append("market=").append(market.getName()).append("&"); //Lowercase
        if (order != null) url.append("order=").append(order.getValue()).append("&"); //Lowercase
        if (sort != null) url.append("sort=").append(sort.getValue()).append("&"); //Lowercase
        if (type != null) url.append("type=").append(type.getValue()).append("&"); //Uppercase
        if (active != null) url.append("active=").append(active).append("&"); //Lowercase
        if (limit != null) url.append("limit=").append(limit).append("&"); //Lowercase
        if (date != null) url.append("date=").append(date).append("&");

        TickerResponse response = massiveClient.getTickers(url.toString());

        if (response.getResults() != null) {
            response.getResults().forEach(this::upsertTicker);
        }

        return response;
    }

    //TickerOverviewResponse isnt necessary to return
    public TickerOverview getTicker(String ticker, LocalDate date) {
        String upperTicker = ticker.toUpperCase();
        Optional<TickerEntity> cachedTicker = tickerRepository.findByTicker(upperTicker);

        if (cachedTicker.isPresent()) {
            TickerEntity tickerEntity = cachedTicker.get();
            Optional<TickerCompanyInfoEntity> cachedInfo = tickerCompanyInfoRepository.findById(tickerEntity.getTickerId());

            boolean tickerFresh = !isStale(tickerEntity.getAccessed());
            boolean infoFresh = cachedInfo.isPresent() && !isStale(cachedInfo.get().getAccessed());

            if (tickerFresh && infoFresh) {
                return mapToOverview(tickerEntity, cachedInfo.get()); // full DB hit
            }
        }

        StringBuilder url = new StringBuilder();
        url.append(ticker.toUpperCase()).append("?");
        if (date != null) {url.append("date=").append(date).append("&");}

        TickerOverviewResponse apiResponse = massiveClient.getTicker(url.toString());
        TickerOverview response = apiResponse != null ? apiResponse.getResults() : null;

        if (response == null) {
            throw new NullPointerException(upperTicker);
        }

        upsertTickerOverview(response);
        return response;
    }

    public TickerTypeResponse getTickerTypes(AssetClass assetClass, Locale locale) {
        StringBuilder url = new StringBuilder();

        if (assetClass != null) {url.append("asset_class=").append(assetClass.getValue()).append("&");} //Lowercase
        if (locale != null) {url.append("locale=").append(locale.getValue()).append("&");} //Lowercase

        return massiveClient.getTickerTypes(url.toString());
    }

    public TickerRelatedResponse getRelatedCompanies(String ticker) {
        StringBuilder url = new StringBuilder();

        url.append(ticker.toUpperCase()).append("?");
        return massiveClient.getRelatedCompanies(url.toString());
    }

    //---------------------------------
    // Helpers and Caching Logic
    //---------------------------------

    private boolean isStale(Instant accessed) {
        return accessed == null || accessed.isBefore(Instant.now().minus(TICKER_TTL));
    }

    private Instant parseInstant(String isoTimestamp) {
        if (isoTimestamp == null || isoTimestamp.isBlank()) return null;
        try {
            return Instant.parse(isoTimestamp);
        } catch (DateTimeParseException e) {
            return null; // or log a warning to notice malformed data without crashing the upsert
        }
    }

    private LocalDate parseLocalDate(String isoDate) {
        if (isoDate == null || isoDate.isBlank()) return null;
        try {
            return LocalDate.parse(isoDate);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    private Optional<TickerEntity> findExisting(String compositeFigi, String ticker,
                                                String primaryExchange, Market market, Locale locale) {
        if (compositeFigi != null) {
            Optional<TickerEntity> byFigi = tickerRepository.findByCompositeFigi(compositeFigi);
            if (byFigi.isPresent()) return byFigi;
        }
        if (primaryExchange != null) {
            return tickerRepository.findByTickerAndPrimaryExchange(ticker, primaryExchange);
        }
        return tickerRepository.findByTickerAndMarketAndLocale(ticker, market, locale);
    }

    private TickerEntity upsertTicker(Ticker ticker) {
        TickerEntity entity = findExisting(ticker.getCompositeFigi(), ticker.getTicker(),
                ticker.getPrimaryExchange(), ticker.getMarket(), ticker.getLocale())
                .orElse(new TickerEntity());

        entity.setCompositeFigi(ticker.getCompositeFigi());
        entity.setTicker(ticker.getTicker());
        entity.setName(ticker.getName());
        entity.setMarket(ticker.getMarket());   // enum straight through, converter handles persistence
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

        return tickerRepository.save(entity);
    }

    private void upsertTickerOverview(TickerOverview overview) {
        TickerEntity tickerEntity = upsertTicker(overview);

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

    private TickerOverview mapToOverview(TickerEntity entity, TickerCompanyInfoEntity info) {
        TickerOverview overview = new TickerOverview();

        overview.setTicker(entity.getTicker());
        overview.setName(entity.getName());
        overview.setMarket(entity.getMarket()); // enum straight through, no conversion needed
        overview.setLocale(entity.getLocale());
        overview.setPrimaryExchange(entity.getPrimaryExchange());
        overview.setCompositeFigi(entity.getCompositeFigi());

        overview.setDescription(info.getDescription());
        overview.setHomepageUrl(info.getHomepageUrl());
        overview.setMarketCap(info.getMarketCap());
        overview.setTotalEmployees(info.getTotalEmployees());
        overview.setListDate(info.getListDate() != null ? info.getListDate().toString() : null);
        overview.setPhoneNumber(info.getPhoneNumber());
        overview.setRoundLot(info.getRoundLot());
        overview.setShareClassSharesOutstanding(info.getShareClassSharesOutstanding());
        overview.setSicCode(info.getSicCode());
        overview.setSicDescription(info.getSicDescription());
        overview.setTickerRoot(info.getTickerRoot());
        overview.setTickerSuffix(info.getTickerSuffix());
        overview.setWeightedSharesOutstanding(info.getWeightedSharesOutstanding());

        return overview;
    }
}
