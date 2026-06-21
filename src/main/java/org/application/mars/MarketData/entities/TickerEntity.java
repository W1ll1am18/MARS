package org.application.mars.MarketData.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.application.mars.MarketData.models.Massive.enums.Locale;
import org.application.mars.MarketData.models.Massive.enums.Market;

import java.time.Instant;

@Entity
@Table(name = "ticker")
@Data
public class TickerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tickerId;

    private Boolean active;
    private String baseCurrencyName;
    private String baseCurrencySymbol;
    private String cik;
    private String compositeFigi;
    private String currencyName;
    private String currencySymbol;
    private Instant delistedUtc;
    private Instant lastUpdatedUtc;
    private Locale locale;
    private Market market;
    private String name;
    private String primaryExchange;
    private String shareClassFigi;
    private String ticker;
    private String type;
    private Instant accessed;
}