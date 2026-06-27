package org.application.mars.MarketData.entities.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
@Data
public class PriceId implements Serializable {
    @Column(name = "ticker_id")
    private Long tickerId;

    @Column(name = "trade_date")
    private LocalDate date;
}