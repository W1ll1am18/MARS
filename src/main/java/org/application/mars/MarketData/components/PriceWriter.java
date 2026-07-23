package org.application.mars.MarketData.components;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.application.mars.MarketData.entities.PriceEntity;
import org.application.mars.MarketData.repository.PriceRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
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

        List<PriceEntity> valid = new ArrayList<>(entities.size());
        for (PriceEntity p : entities) {
            if (isValidBar(p)) {
                valid.add(p);
            }
//            else {
//                log.warn("Rejected invalid price bar: tickerId={}, date={}, o={}, h={}, l={}, c={}, v={}",
//                        p.getId().getTickerId(), p.getId().getDate(),
//                        p.getOpen(), p.getHigh(), p.getLow(), p.getClose(), p.getVolume());
//            }
        }

        if (valid.isEmpty()) return;

        int n = valid.size();
        Long[]       tickerIds = new Long[n];
        String[]     dates     = new String[n];
        BigDecimal[] opens     = new BigDecimal[n];
        BigDecimal[] highs     = new BigDecimal[n];
        BigDecimal[] lows      = new BigDecimal[n];
        BigDecimal[] closes    = new BigDecimal[n];
        Long[]       volumes   = new Long[n];

        for (int i = 0; i < n; i++) {
            PriceEntity p = valid.get(i);
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

    //Some fields from MASSIVE may be corrupted or numbers that are can't exist, this is to ensure consistency
    private boolean isValidBar(PriceEntity p) {
        BigDecimal o = p.getOpen(), h = p.getHigh(), l = p.getLow(), c = p.getClose();
        Long v = p.getVolume();

        if (o == null || h == null || l == null || c == null || v == null) return false;

        BigDecimal maxPlausiblePrice = new BigDecimal("1000000");
        if (o.compareTo(BigDecimal.ZERO) <= 0 || o.compareTo(maxPlausiblePrice) > 0) return false;
        if (h.compareTo(BigDecimal.ZERO) <= 0 || h.compareTo(maxPlausiblePrice) > 0) return false;
        if (l.compareTo(BigDecimal.ZERO) <= 0 || l.compareTo(maxPlausiblePrice) > 0) return false;
        if (c.compareTo(BigDecimal.ZERO) <= 0 || c.compareTo(maxPlausiblePrice) > 0) return false;

        if (h.compareTo(l) < 0) return false;
        if (o.compareTo(l) < 0 || o.compareTo(h) > 0) return false;
        if (c.compareTo(l) < 0 || c.compareTo(h) > 0) return false;

        if (v < 0) return false;

        return true;
    }
}
