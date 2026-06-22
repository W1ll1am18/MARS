package org.application.mars.MarketData.models.Finnhub.CompanyInfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BasicFinancialsResponse {
    private String symbol;
    private FinancialMetrics metric;
}