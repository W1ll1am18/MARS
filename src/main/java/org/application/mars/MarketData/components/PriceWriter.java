package org.application.mars.MarketData.components;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.application.mars.MarketData.entities.PriceEntity;
import org.application.mars.MarketData.repository.PriceRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PriceWriter {
    private final PriceRepository priceRepository;

    //    saveAll vs upsert
    //    saveAll on a fresh new PriceEntity() always attempts an INSERT.
    //    If you call getPrices twice for the same ticker in quick succession
    //    (before staleness logic kicks in, or because of a race), the second call tries to insert the same
    //    (ticker_id, trade_date) rows and throws a primary key violation, since PriceId is a composite key.
    //    saveAll on existing entities works correctly only if those entities were loaded from the DB first
    //    (so Hibernate knows to UPDATE instead of INSERT) — but you're constructing fresh new PriceEntity()
    //    objects, not loading existing ones, so Hibernate always treats them as new inserts.

    @Transactional
    public void batchInsert(List<PriceEntity> entities) {
        if (entities.isEmpty()) return;

        int n = entities.size();
        Long[]       tickerIds = new Long[n];
        String[]     dates     = new String[n];
        BigDecimal[] opens     = new BigDecimal[n];
        BigDecimal[] highs     = new BigDecimal[n];
        BigDecimal[] lows      = new BigDecimal[n];
        BigDecimal[] closes    = new BigDecimal[n];
        Long[]       volumes   = new Long[n];

        for (int i = 0; i < n; i++) {
            PriceEntity p = entities.get(i);
            tickerIds[i] = p.getId().getTickerId();
            dates[i]     = p.getId().getDate().toString();
            opens[i]     = p.getOpen();
            highs[i]     = p.getHigh();
            lows[i]      = p.getLow();
            closes[i]    = p.getClose();
            volumes[i]   = p.getVolume();
        }

        priceRepository.batchUpsert(tickerIds, dates, opens, highs, lows, closes, volumes);
    }
}
