package org.application.mars.MarketData.service;

import lombok.RequiredArgsConstructor;
import org.application.mars.Common.utils.Constant;
import org.application.mars.MarketData.components.PriceWriter;
import org.application.mars.MarketData.client.MassiveClient;
import org.application.mars.MarketData.dtos.OHLCVBarDTO;
import org.application.mars.MarketData.dtos.OHLCVDTO;
import org.application.mars.MarketData.entities.MarketHolidayEntity;
import org.application.mars.MarketData.entities.PriceEntity;
import org.application.mars.MarketData.entities.TickerEntity;
import org.application.mars.MarketData.entities.id.PriceId;
import org.application.mars.MarketData.models.Massive.AggregateBars.CustomBars;
import org.application.mars.MarketData.models.Massive.AggregateBars.CustomBarsResponse;
import org.application.mars.MarketData.models.Massive.enums.Input.Order;
import org.application.mars.MarketData.models.Massive.enums.Input.Timespan;
import org.application.mars.MarketData.repository.HolidayRepository;
import org.application.mars.MarketData.repository.PriceRepository;
import org.application.mars.MarketData.repository.TickerRepository;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PriceChartService {
    private final MassiveClient massiveClient;
    private final HolidayRepository holidayRepository;
    private final TickerRepository tickerRepository;
    private final PriceRepository priceRepository;
    private final TickerService tickerService;
    private final PriceWriter priceWriter;

    public OHLCVDTO getPrices (String stocksTicker, Long multiplier, Timespan timeSpan, LocalDate from,
                               LocalDate to, Boolean adjusted, Order order, Integer limit) {

        String upperTicker = stocksTicker.toUpperCase();

        //Upsert first if missing then return db data
        //Find if ticker exists
        TickerEntity ticker = tickerRepository.findByTicker(stocksTicker)
            .orElseGet(() -> {
                // ticker not cached yet — fetch it from MASSIVE first
                tickerService.getTickers(upperTicker, null, null, null, null, null,
                        null, null, null, null, null, null);
                return tickerRepository.findByTicker(upperTicker)
                    .orElseThrow(() -> new IllegalArgumentException("Ticker not found: " + upperTicker));
            });

        LocalDate[] range = updatePrices(ticker, stocksTicker, multiplier, timeSpan, from, to, adjusted, order, limit);
        LocalDate resolvedFrom = range[0];
        LocalDate resolvedTo   = range[1];

        List<PriceEntity> prices = priceRepository.findPricesInRange(ticker.getTickerId(), resolvedFrom, resolvedTo);

        //TODO for now this will be the max
        List<PriceEntity> limited = (limit != null && limit < prices.size())
                ? prices.subList(prices.size() - limit, prices.size()) // most recent N bars
                : prices;

        return getOhlcvdto(limited);
    }

    private LocalDate[] updatePrices(TickerEntity ticker, String stocksTicker, Long multiplier, Timespan timeSpan, LocalDate from,
                               LocalDate to, Boolean adjusted, Order order, Integer limit) {

        List<PriceEntity> priceEntities = new ArrayList<>();
        List<CustomBars> bars;

        //Check if info is up to date
        LocalDate latestDbDate = priceRepository.findLatestTradeDate(ticker);
        LocalDate resolvedTo = (to != null) ? to : getLatestTradingDay(LocalDate.now(ZoneId.of("America/New_York")));
        LocalDate resolvedFrom = (from != null) ? from : resolvedTo.minusYears(Constant.YEARS_OF_DATA);
        LocalDate fetchFrom;

        if (latestDbDate == null) {
            //If no data present fetch from 5 years ago
            fetchFrom = resolvedFrom;
        }
        else if (isStale(latestDbDate)) {
            //If data is present and not up to do date
            fetchFrom = latestDbDate.plusDays(1);
        }
        else {
            //If data exists and up to date
            fetchFrom = null;
        }

        if (fetchFrom != null && fetchFrom.isBefore(resolvedTo.plusDays(1))) {
            bars = getCustomBars(stocksTicker, multiplier, timeSpan, fetchFrom, resolvedTo, adjusted, order, limit);
            for (CustomBars bar : bars) {
                priceEntities.add(mapToPrice(ticker, formatUnixToLocalDate(bar.getT()), bar));
            }
            priceWriter.batchInsert(priceEntities);
        }

        return new LocalDate[]{ resolvedFrom, resolvedTo };
    }

    private OHLCVDTO getOhlcvdto(List<PriceEntity> prices) {
        List<OHLCVBarDTO> barDTOs = new ArrayList<>();

        for (PriceEntity p : prices) {
            OHLCVBarDTO dto = new OHLCVBarDTO();

            dto.setTime(p.getId().getDate().toString());
            dto.setOpen(p.getOpen());
            dto.setHigh(p.getHigh());
            dto.setLow(p.getLow());
            dto.setClose(p.getClose());
            dto.setVolume(p.getVolume());
            barDTOs.add(dto);
        }

        OHLCVDTO chart = new OHLCVDTO();
        chart.setResults(barDTOs);
        return chart;
    }

    private PriceEntity mapToPrice(TickerEntity tickerEntity, LocalDate date, CustomBars bar) {
        PriceId id = new PriceId();
        id.setTickerId(tickerEntity.getTickerId());
        id.setDate(date);

        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setId(id);

        priceEntity.setTicker(tickerEntity);
        priceEntity.setOpen(bar.getO());
        priceEntity.setHigh(bar.getH());
        priceEntity.setLow(bar.getL());
        priceEntity.setClose(bar.getC());
        priceEntity.setVolume(bar.getV());

        return priceEntity;
    }

    private ArrayList<CustomBars> getCustomBars(String stocksTicker, Long multiplier, Timespan timeSpan, LocalDate from,
                                                LocalDate to, Boolean adjusted, Order order, Integer limit) {

        ArrayList<CustomBars> allBars = new ArrayList<>();
        int effectiveLimit = (limit != null) ? limit : 50000;
        StringBuilder url = new StringBuilder();

        url.append("ticker/").append(stocksTicker.toUpperCase()).append("/");
        url.append("range/").append(multiplier).append("/");
        url.append(timeSpan.getValue()).append("/");
        url.append(from).append("/");
        url.append(to).append("?");
        url.append("limit=").append(effectiveLimit).append("&");

        if (adjusted != null) {url.append("adjusted=").append(adjusted).append("&");}
        if (order != null) {url.append("sort=").append(order.getValue()).append("&");}

        //Massive Paginates their bar response so we need to follow their urls
        CustomBarsResponse response;
        String nextUrl = url.toString();

        do {
            response = massiveClient.getCustomBars(nextUrl);
            if (response.getResults() != null) {
                allBars.addAll(response.getResults());
            }
            nextUrl = response.getNextUrl(); // null when no more pages exist
        } while (nextUrl != null);

        return allBars;
    }

    private boolean isStale(LocalDate latestDbDate) {
        LocalDate latestTradingDay = getLatestTradingDay(LocalDate.now(ZoneId.of("America/New_York")));
        return latestDbDate.isBefore(latestTradingDay);
    }

    private LocalDate getLatestTradingDay(LocalDate date) {
        ZoneId ny = ZoneId.of("America/New_York");
        LocalTime now = LocalTime.now(ny);
        DayOfWeek dow = date.getDayOfWeek();

        if (dow == DayOfWeek.SATURDAY) {
            return getLatestTradingDay(date.minusDays(1));
        }
        if (dow == DayOfWeek.SUNDAY) {
            return getLatestTradingDay(date.minusDays(2));
        }

        Optional<MarketHolidayEntity> holiday = holidayRepository.findByHolidayDate(date);
        if (holiday.isPresent()) {
            if (holiday.get().getStatus() != null && "closed".equals(holiday.get().getStatus())) {
                return getLatestTradingDay(date.minusDays(1));
            }
        }

        boolean isToday = date.equals(LocalDate.now(ny));
        if (isToday && now.isBefore(LocalTime.of(16, 30))) {
            return getLatestTradingDay(date.minusDays(1));
        }

        return date;
    }

    private LocalDate formatUnixToLocalDate(long epochMillis) {
        return Instant.ofEpochMilli(epochMillis)
            .atZone(ZoneId.of("America/New_York"))
            .toLocalDate();
    }

}
