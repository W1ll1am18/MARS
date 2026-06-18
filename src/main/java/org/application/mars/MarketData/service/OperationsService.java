package org.application.mars.MarketData.service;

import lombok.RequiredArgsConstructor;
import org.application.mars.MarketData.client.MassiveClient;
import org.application.mars.MarketData.models.Massive.Operations.ConditionCodesResponse;
import org.application.mars.MarketData.models.Massive.Operations.ExchangesResponse;
import org.application.mars.MarketData.models.Massive.Operations.MarketHolidaysResponse;
import org.application.mars.MarketData.models.Massive.Operations.MarketStatusResponse;
import org.application.mars.MarketData.models.Massive.enums.AssetClassExchange;
import org.application.mars.MarketData.models.Massive.enums.Input.DataType;
import org.application.mars.MarketData.models.Massive.enums.Input.Order;
import org.application.mars.MarketData.models.Massive.enums.Input.Sip;
import org.application.mars.MarketData.models.Massive.enums.Input.SortConditionCodes;
import org.application.mars.MarketData.models.Massive.enums.Locale;
import org.application.mars.MarketData.models.Massive.enums.Output.AssetClassCondition;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationsService {
    private final MassiveClient massiveClient;

    public ExchangesResponse getExchanges(AssetClassExchange assetClass, Locale locale) {
        StringBuilder url = new StringBuilder();

        if (assetClass != null) {url.append("asset_class=").append(assetClass.getValue()).append("&");}
        if (locale != null) {url.append("locale=").append(locale.getValue()).append("&");}

        return massiveClient.getExchanges(url.toString());
    }

    public List<MarketHolidaysResponse> getMarketHolidays() {
        return massiveClient.getMarketHolidays();
    }

    public MarketStatusResponse getMarketStatus() {
        return massiveClient.getMarketStatus();
    }

    public ConditionCodesResponse getConditionCodes(AssetClassCondition assetClass, DataType dataType,
                                                    Integer id, Sip sip, Order order,
                                                    Integer limit, SortConditionCodes sort) {
        StringBuilder url = new StringBuilder();

        if (assetClass != null) {url.append("asset_class=").append(assetClass.getValue()).append("&");}
        if (dataType != null) {url.append("data_type=").append(dataType.getValue()).append("&");}
        if (id != null) {url.append("id=").append(id).append("&");}
        if (sip != null) {url.append("sip=").append(sip.getValue()).append("&");}
        if (order != null) {url.append("order=").append(order.getValue()).append("&");}
        if (limit != null) {url.append("limit=").append(limit).append("&");}
        if (sort != null) {url.append("sort=").append(sort.getValue()).append("&");}

        return massiveClient.getConditionCodes(url.toString());
    }
}