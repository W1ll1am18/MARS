package org.application.mars.MarketData.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.application.mars.MarketData.entities.id.PriceId;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Entity
@Table(name = "price")
public class PriceEntity {

    @EmbeddedId
    private PriceId id;

    @ManyToOne
    @MapsId("tickerId")
    @JoinColumn(name = "ticker_id")
    private TickerEntity ticker;
    private BigDecimal open;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal close;
    private Long volume;
}
