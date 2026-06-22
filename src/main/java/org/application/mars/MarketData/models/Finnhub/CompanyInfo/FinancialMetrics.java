package org.application.mars.MarketData.models.Finnhub.CompanyInfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FinancialMetrics {
    //Beginner Layer

    @JsonProperty("peBasicExclExtraTTM")
    private Double peRatioTTM;              // Trailing 12-month P/E

    @JsonProperty("peExclExtraAnnual")
    private Double peRatioAnnual;           // Annual P/E — useful to show both

    @JsonProperty("forwardPE")
    private Double forwardPE;               // Forward P/E — great for education

    @JsonProperty("pb")
    private Double priceToBook;             // Current P/B (not pbAnnual — pb is most current)

    @JsonProperty("marketCapitalization")
    private Double marketCap;               // In millions

    @JsonProperty("52WeekHigh")
    private Double weekHigh52;

    @JsonProperty("52WeekHighDate")
    private String weekHigh52Date;

    @JsonProperty("52WeekLow")
    private Double weekLow52;

    @JsonProperty("52WeekLowDate")
    private String weekLow52Date;

    @JsonProperty("dividendYieldIndicatedAnnual")
    private Double dividendYield;

    @JsonProperty("dividendIndicatedAnnual")
    private Double dividendPerYear;         // Actual dollar amount e.g. $1.08

    @JsonProperty("beta")
    private Double beta;                    // Moved here — beginner-friendly volatility metric

    @JsonProperty("epsTTM")
    private Double epsTTM;                  // Earnings per share — needed to explain P/E

    //Intermediate layer

    @JsonProperty("grossMarginTTM")
    private Double grossMarginTTM;

    @JsonProperty("grossMarginAnnual")
    private Double grossMarginAnnual;

    @JsonProperty("netProfitMarginTTM")
    private Double netProfitMarginTTM;

    @JsonProperty("netProfitMarginAnnual")
    private Double netProfitMarginAnnual;

    @JsonProperty("operatingMarginTTM")
    private Double operatingMarginTTM;      // Good addition — between gross and net

    @JsonProperty("roeRfy")
    private Double returnOnEquity;

    @JsonProperty("roaTTM")
    private Double returnOnAssets;          // roaTTM is more current than roaRfy

    @JsonProperty("roiAnnual")
    private Double returnOnInvestment;      // Useful alongside ROE/ROA

    @JsonProperty("currentRatioQuarterly")
    private Double currentRatio;            // Most recent quarter is most relevant

    @JsonProperty("quickRatioQuarterly")
    private Double quickRatio;              // Stricter version of current ratio

    @JsonProperty("totalDebt/totalEquityAnnual")
    private Double debtToEquity;

    @JsonProperty("payoutRatioAnnual")
    private Double payoutRatio;             // % of earnings paid as dividends

    //Advanced layer

    @JsonProperty("evEbitdaTTM")
    private Double evEbitdaTTM;             // EV/EBITDA — already computed, no need to calculate

    @JsonProperty("enterpriseValue")
    private Double enterpriseValue;         // In millions

    @JsonProperty("revenueGrowth3Y")
    private Double revenueGrowth3Year;

    @JsonProperty("revenueGrowth5Y")
    private Double revenueGrowth5Year;

    @JsonProperty("revenueGrowthTTMYoy")
    private Double revenueGrowthYoy;        // Most recent YoY — good for current momentum

    @JsonProperty("epsGrowth3Y")
    private Double epsGrowth3Year;

    @JsonProperty("epsGrowth5Y")
    private Double epsGrowth5Year;

    @JsonProperty("epsGrowthTTMYoy")
    private Double epsGrowthYoy;

    @JsonProperty("pegTTM")
    private Double pegRatio;                // P/E divided by growth rate — advanced valuation

    @JsonProperty("forwardPEG")
    private Double forwardPegRatio;

    @JsonProperty("currentEv/freeCashFlowTTM")
    private Double evFreeCashFlow;          // EV/FCF — alternative to EV/EBITDA

    @JsonProperty("pfcfShareTTM")
    private Double priceToFreeCashFlow;     // Price/FCF per share

    //TODO Revist series block after ML and bar charts are implemented. bookValue, eps, revenue
}
