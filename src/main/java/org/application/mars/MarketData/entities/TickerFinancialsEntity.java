package org.application.mars.MarketData.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(name = "ticker_financials")
@Data
public class TickerFinancialsEntity {

    @Id
    private Long tickerId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "ticker_id")
    private TickerEntity ticker;

    // ── Valuation ─────────────────────────────────────────────
    @Column(name = "pe_ratio_ttm")
    private Double peRatioTTM;

    @Column(name = "pe_ratio_annual")
    private Double peRatioAnnual;

    @Column(name = "forward_pe")
    private Double forwardPE;

    private Double priceToBook;
    private Double pegRatio;

    @Column(name = "forward_peg_ratio")
    private Double forwardPegRatio;

    @Column(name = "ev_ebitda_ttm")
    private Double evEbitdaTTM;

    private Double evFreeCashFlow;
    private Double priceToFreeCashFlow;
    private Double enterpriseValue;
    private Double dividendYield;
    private Double dividendPerYear;

    // ── Price metrics ─────────────────────────────────────────
    @Column(name = "week_high_52")
    private Double weekHigh52;

    @Column(name = "week_high_52_date")
    private String weekHigh52Date;

    @Column(name = "week_low_52")
    private Double weekLow52;

    @Column(name = "week_low_52_date")
    private String weekLow52Date;

    private Double beta;

    @Column(name = "eps_ttm")
    private Double epsTTM;

    // ── Profitability ─────────────────────────────────────────
    @Column(name = "gross_margin_ttm")
    private Double grossMarginTTM;

    private Double grossMarginAnnual;

    @Column(name = "operating_margin_ttm")
    private Double operatingMarginTTM;

    @Column(name = "net_profit_margin_ttm")
    private Double netProfitMarginTTM;

    private Double netProfitMarginAnnual;
    private Double returnOnEquity;
    private Double returnOnAssets;
    private Double returnOnInvestment;
    private Double payoutRatio;

    // ── Financial health ──────────────────────────────────────
    private Double currentRatio;
    private Double quickRatio;
    private Double debtToEquity;

    // ── Growth ────────────────────────────────────────────────
    private Double revenueGrowthYoy;

    @Column(name = "revenue_growth_3y")
    private Double revenueGrowth3y;

    @Column(name = "revenue_growth_5y")
    private Double revenueGrowth5y;

    private Double epsGrowthYoy;

    @Column(name = "eps_growth_3y")
    private Double epsGrowth3y;

    @Column(name = "eps_growth_5y")
    private Double epsGrowth5y;

    // ── Cache control ─────────────────────────────────────────
    private Instant accessed;
}
