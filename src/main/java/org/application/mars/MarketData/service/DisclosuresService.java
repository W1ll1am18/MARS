package org.application.mars.MarketData.service;

import lombok.RequiredArgsConstructor;
import org.application.mars.MarketData.client.MassiveClient;
import org.application.mars.MarketData.models.Disclosures.IndexResponse;
import org.application.mars.MarketData.models.Disclosures.RiskCategoriesResponse;
import org.application.mars.MarketData.models.Disclosures.RiskFactorsResponse;
import org.application.mars.MarketData.models.Disclosures.TenKSectionsResponse;
import org.application.mars.MarketData.models.Massive.enums.Input.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DisclosuresService {
    private final MassiveClient massiveClient;

    //TODO More filters in, cik, ticker, form_type, filing_date
    public IndexResponse getIndexResponse(String cik, String ticker, String formType, LocalDate filingDate, Integer limit, SortIndex column, Order order) {
        StringBuilder url =  new StringBuilder();

        if (cik != null) {url.append("cik=").append(cik).append("&");}
        if (ticker != null) {url.append("ticker=").append(ticker).append("&");}
        if (formType != null) {url.append("form_type=").append(formType).append("&");}
        if (filingDate != null) {url.append("filing_date=").append(filingDate).append("&");}
        if (limit != null) {url.append("limit=").append(limit).append("&");}
        if (column != null && order != null) {url.append("column=").append(column.getValue()).append(".").append(order.getValue()).append("&");}

        return massiveClient.getIndexResponse(url.toString());
    }

    //TODO More filters in cik, ticker, section, filing_date, period_end
    public TenKSectionsResponse getTenKSections(String cik, String ticker, Section section, LocalDate filingDate, LocalDate periodEnd, Integer limit, SortTenK column, Order order) {
        StringBuilder url = new StringBuilder();

        if (cik != null) {url.append("cik=").append(cik).append("&");}
        if (ticker != null) {url.append("ticker=").append(ticker).append("&");}
        if (section != null) {url.append("section=").append(section.getValue()).append("&");}
        if (filingDate != null) {url.append("filing_date=").append(filingDate).append("&");}
        if (periodEnd != null) {url.append("period_end=").append(periodEnd).append("&");}
        if (limit != null) {url.append("limit=").append(limit).append("&");}
        if (column != null && order != null) {url.append("sort=").append(column.getValue()).append(".").append(order.getValue()).append("&");}

        return massiveClient.getTenKSections(url.toString());
    }

    //TODO More filters in cik, ticker, filing_date
    public RiskFactorsResponse getRiskFactors(LocalDate filingDate, String ticker, String cik, Integer limit, SortRiskFactors column, Order order) {
        StringBuilder url = new StringBuilder();

        if (cik != null) {url.append("cik=").append(cik).append("&");}
        if (ticker != null) {url.append("ticker=").append(ticker).append("&");}
        if (filingDate != null) {url.append("filing_date=").append(filingDate).append("&");}
        if (limit != null) {url.append("limit=").append(limit).append("&");}
        if (column != null && order != null) {url.append("sort=").append(column.getValue()).append(".").append(order.getValue()).append("&");}

        return massiveClient.getRiskFactors(url.toString());
    }

    //TODO taxonomy, primary_catetgory, secondary_category, tertiary_category
    public RiskCategoriesResponse getRiskCategories(Double taxonomy, String primaryCategory, String secondaryCategory,
                                                    String tertiaryCategory, Integer limit, SortRiskCategory column, Order order) {
        StringBuilder url = new StringBuilder();

        if (taxonomy != null) {url.append("taxonomy=").append(taxonomy).append("&");}
        if (primaryCategory != null) {url.append("primary_category=").append(primaryCategory).append("&");}
        if (secondaryCategory != null) {url.append("secondary_category=").append(secondaryCategory).append("&");}
        if (tertiaryCategory != null) {url.append("tertiary_category=").append(tertiaryCategory).append("&");}
        if (limit != null) {url.append("limit=").append(limit).append("&");}
        if (column != null && order != null) {url.append("sort=").append(column.getValue()).append(".").append(order.getValue()).append("&");}

        return massiveClient.getRiskCategories(url.toString());
    }
}
