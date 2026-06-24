package org.application.mars.MarketData.service;

import lombok.RequiredArgsConstructor;
import org.application.mars.MarketData.client.FinnhubClient;
import org.application.mars.MarketData.models.Finnhub.CompanyInfo.BasicFinancialsResponse;
import org.application.mars.MarketData.models.Finnhub.CompanyInfo.FinancialMetrics;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyMetricsService {
    private final FinnhubClient finnhubClient;

    public FinancialMetrics getCompanyMetrics(String ticker) {
        StringBuilder url = new StringBuilder();

        url.append(ticker).append("&");
        url.append("metric=all").append("&");

        BasicFinancialsResponse apiResponse = finnhubClient.getBasicFinancials(url.toString());
        FinancialMetrics response = apiResponse != null ? apiResponse.getMetric() : null;

        if (response == null) {
            throw new NullPointerException("Metric response is null");
        }

        return response;
    }
}
