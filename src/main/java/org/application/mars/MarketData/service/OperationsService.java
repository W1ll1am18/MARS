package org.application.mars.MarketData.service;

import lombok.RequiredArgsConstructor;
import org.application.mars.MarketData.client.MassiveClient;
import org.application.mars.MarketData.models.Massive.Operations.ExchangesResponse;
import org.application.mars.MarketData.models.Massive.enums.AssetClassExchange;
import org.application.mars.MarketData.models.Massive.enums.Locale;
import org.springframework.stereotype.Service;

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
}
