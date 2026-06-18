package org.application.mars.MarketData.controllers;

import lombok.RequiredArgsConstructor;
import org.application.mars.MarketData.models.Massive.enums.Input.*;
import org.application.mars.MarketData.service.DisclosuresService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/disclosures")
public class DisclosuresController {
    private final DisclosuresService disclosuresService;

    @GetMapping("/index") //SEC EDGAR
    public ResponseEntity<?> getIndex (
            @RequestParam(required = false) String cik,
            @RequestParam(required = false) String ticker,
            @RequestParam(required = false) String formType,
            @RequestParam(required = false) LocalDate filingDate,
            @RequestParam(required = false, defaultValue = "1000") Integer limit,
            @RequestParam(required = false, defaultValue = "FILING_DATE") SortIndex column,
            @RequestParam(required = false, defaultValue = "DESC") Order order
    ) {
        return ResponseEntity.ok(disclosuresService.getIndexResponse(cik, ticker, formType, filingDate, limit, column, order));
    }

    @GetMapping("/TenK")
    public ResponseEntity<?> getTenKSections (
            @RequestParam(required = false) String cik,
            @RequestParam(required = false) String ticker,
            @RequestParam(required = false) LocalDate filingDate,
            @RequestParam(required = false) LocalDate periodEnd,
            @RequestParam(required = false) Section section,
            @RequestParam(required = false, defaultValue = "100") Integer limit,
            @RequestParam(required = false, defaultValue = "PERIOD_END") SortTenK column,
            @RequestParam(required = false, defaultValue = "DESC") Order order
    ) {
        return ResponseEntity.ok(disclosuresService.getTenKSections(cik, ticker, section, filingDate, periodEnd, limit, column, order));
    }

    @GetMapping("/riskFactors")
    public ResponseEntity<?> getRiskFactors (
            @RequestParam(required = false) String cik,
            @RequestParam(required = false) String ticker,
            @RequestParam(required = false) LocalDate filingDate,
            @RequestParam(required = false, defaultValue = "100") Integer limit,
            @RequestParam(required = false, defaultValue = "FILING_DATE") SortRiskFactors column,
            @RequestParam(required = false, defaultValue = "DESC") Order order
    ) {
        return ResponseEntity.ok(disclosuresService.getRiskFactors(filingDate, ticker, cik, limit, column, order));
    }

    @GetMapping("/riskCategories")
    public ResponseEntity<?> getRiskCategories (
            @RequestParam(required = false) Double taxonomy,
            @RequestParam(required = false) String primaryCategory,
            @RequestParam(required = false) String secondaryCategory,
            @RequestParam(required = false) String tertiaryCategory,
            @RequestParam(required = false, defaultValue = "200") Integer limit,
            @RequestParam(required = false, defaultValue = "TAXONOMY") SortRiskCategory column,
            @RequestParam(required = false, defaultValue = "DESC") Order order
    ) {
        return ResponseEntity.ok(disclosuresService.getRiskCategories(taxonomy, primaryCategory, secondaryCategory, tertiaryCategory, limit, column, order));
    }
}
