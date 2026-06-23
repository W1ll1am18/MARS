package org.application.mars.MarketData.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "ticker_company_info")
@Data
public class TickerCompanyInfoEntity {

    @Id
    private Long tickerId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "ticker_id")
    private TickerEntity ticker;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String homepageUrl;
    private LocalDate listDate;
    private Long marketCap;
    private String phoneNumber;
    private Integer roundLot;
    private Long shareClassSharesOutstanding;
    private String sicCode;
    private String sicDescription;
    private String tickerRoot;
    private String tickerSuffix;
    private Long totalEmployees;
    private Long weightedSharesOutstanding;
    private Instant accessed;
}