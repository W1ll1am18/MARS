package org.application.mars.MarketData.service;

import lombok.RequiredArgsConstructor;
import org.application.mars.MarketData.client.MassiveClient;
import org.application.mars.MarketData.models.Massive.CorporateActions.*;
import org.application.mars.MarketData.models.Massive.enums.Input.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CorporateService {
    private final MassiveClient massiveClient;

    //TODO Listing date has more filters
    public IPOResponse getIPOResponse(String ticker, String usCode, String isin,
                                      LocalDate listingDate, IPOStatus ipoStatus,
                                      Order order, Integer limit, SortIPO sort) {
        StringBuilder url = new StringBuilder();

        if (ticker != null) {url.append("ticker=").append(ticker).append("&");}
        if (usCode != null) {url.append("us_code=").append(usCode).append("&");}
        if (isin != null) {url.append("isin=").append(isin).append("&");}
        if (listingDate != null) {url.append("listing_date=").append(listingDate).append("&");}
        if (ipoStatus != null) {url.append("ipo_status=").append(ipoStatus).append("&");}
        if (order != null) {url.append("order=").append(order).append("&");}
        if (limit != null) {url.append("limit=").append(limit).append("&");}
        if (sort != null) {url.append("sort=").append(sort).append("&");}

        return massiveClient.getIPOs(url.toString());
    }

    //TODO ticker, executionDate, adjustmentType has more filters
    public SplitsResponse getSplits(String ticker, LocalDate executionDate, AdjustmentType adjustmentType,
                                    Integer limit, SortSplits column, Order order) {
        StringBuilder url = new StringBuilder();

        if (ticker != null) {url.append("ticker=").append(ticker).append("&");}
        if (executionDate != null) {url.append("execution_date=").append(executionDate).append("&");}
        if (adjustmentType != null) {url.append("adjustment_type=").append(adjustmentType).append("&");}
        if (limit != null) {url.append("limit=").append(limit).append("&");}
        if (column != null && order != null) {url.append("sort=").append(column.getValue()).append(".").append(order.getValue()).append("&");}

        return massiveClient.getSplits(url.toString());
    }

    //TODO ticker, ex_dividend_date, distribution_type has more filters
    public DividendsResponse getDividends(String ticker, LocalDate exDividendDate, Integer frequency,
                                          DistributionType distributionType, Integer limit, SortDividend column,
                                          Order order) {
        StringBuilder url = new StringBuilder();

        if (ticker != null) {url.append("ticker=").append(ticker).append("&");}
        if (exDividendDate != null) {url.append("ex_dividend_date=").append(exDividendDate).append("&");}
        if (frequency != null) {url.append("frequency=").append(frequency).append("&");}
        if (distributionType != null) {url.append("distribution_type=").append(distributionType).append("&");}
        if (limit != null) {url.append("limit=").append(limit).append("&");}
        if (column != null && order != null) {url.append("sort=").append(column.getValue()).append(".").append(order.getValue()).append("&");}

        return massiveClient.getDividends(url.toString());
    }

    //id can be TICKER, CUSIP, COMPOSITE FIGI
    public TickerEventsResponse getTickerEvents(String id, TypeTickerEvent types) {
        StringBuilder url = new StringBuilder();

        if (id != null) {url.append("/").append(id).append("/events?");}
        if (types != null) {url.append("types=").append(types.getValue()).append("&");}

        return massiveClient.getTickerEvents(url.toString());
    }
}
