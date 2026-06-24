package org.application.mars.MarketData.service;

import lombok.RequiredArgsConstructor;
import org.application.mars.MarketData.client.MassiveClient;
import org.application.mars.MarketData.dtos.OHLCVBarDTO;
import org.application.mars.MarketData.dtos.OHLCVDTO;
import org.application.mars.MarketData.models.Massive.AggregateBars.CustomBars;
import org.application.mars.MarketData.models.Massive.AggregateBars.CustomBarsResponse;
import org.application.mars.MarketData.models.Massive.enums.Input.Order;
import org.application.mars.MarketData.models.Massive.enums.Input.Timespan;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceChartService {
    private final MassiveClient massiveClient;

    public OHLCVDTO getPrices (String stocksTicker, Long multiplier, Timespan timeSpan, LocalDate from,
                               LocalDate to, Boolean adjusted, Order order, Integer limit) {

        List<CustomBars> bars = getCustomBars(stocksTicker, multiplier, timeSpan, from, to, adjusted, order, limit);
        List<OHLCVBarDTO> barDTOs = new ArrayList<>();
        OHLCVDTO chart = new OHLCVDTO();

        for (CustomBars bar : bars) {
            OHLCVBarDTO barDTO = new OHLCVBarDTO();
            barDTO.setTime(formatDate(bar.getT()));
            barDTO.setOpen(bar.getO());
            barDTO.setHigh(bar.getH());
            barDTO.setLow(bar.getL());
            barDTO.setClose(bar.getC());
            barDTO.setVolume(bar.getV());
            barDTOs.add(barDTO);
        }

        chart.setResults(barDTOs);
        return chart;
    }

    private ArrayList<CustomBars> getCustomBars(String stocksTicker, Long multiplier, Timespan timeSpan, LocalDate from,
                                                LocalDate to, Boolean adjusted, Order order, Integer limit) {

        StringBuilder url = new StringBuilder();

        url.append("ticker/").append(stocksTicker.toUpperCase()).append("/");
        url.append("range/").append(multiplier).append("/");
        url.append(timeSpan.getValue()).append("/");
        url.append(from).append("/");
        url.append(to).append("?");

        if (adjusted != null) {url.append("adjusted=").append(adjusted).append("&");}
        if (order != null) {url.append("sort=").append(order.getValue()).append("&");}
        if (limit != null) {url.append("limit=").append(limit).append("&");}

        CustomBarsResponse customBars = massiveClient.getCustomBars(url.toString());
        if (customBars.getResults() != null) {
            return customBars.getResults();
        }
        else {
            throw new NullPointerException("No results found for " + stocksTicker);
        }
    }

    private String formatDate(long epochMillis) {
        return Instant.ofEpochMilli(epochMillis)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
            .format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

}
