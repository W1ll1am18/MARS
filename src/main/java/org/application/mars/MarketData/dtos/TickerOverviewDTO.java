package org.application.mars.MarketData.dtos;

import lombok.Data;

@Data
public class TickerOverviewDTO {
    // From MASSIVE (company overview)
    private String ticker;
    private String name;
    private String market;
    private String locale;
    private String primaryExchange;
    private String compositeFigi;
    private String description;
    private String homepageUrl;
    private String listDate;
    private String phoneNumber;
    private Integer roundLot;
    private Long shareClassSharesOutstanding;
    private String sicCode;
    private String sicDescription;
    private String tickerRoot;
    private String tickerSuffix;
    private Long totalEmployees;
    private Long weightedSharesOutstanding;
    private Long marketCap;

    // From Finnhub (financial metrics)
    private Double peRatioTTM;
    private Double forwardPE;
    private Double priceToBook;
    private Double beta;
    private Double weekHigh52;
    private String weekHigh52Date;
    private Double weekLow52;
    private String weekLow52Date;
    private Double dividendYield;
    private Double dividendPerYear;
    private Double epsTTM;
    private Double grossMarginTTM;
    private Double netProfitMarginTTM;
    private Double operatingMarginTTM;
    private Double returnOnEquity;
    private Double returnOnAssets;
    private Double currentRatio;
    private Double quickRatio;
    private Double debtToEquity;
    private Double payoutRatio;
    private Double evEbitdaTTM;
    private Double revenueGrowthYoy;
    private Double epsGrowthYoy;
    private Double pegRatio;
}