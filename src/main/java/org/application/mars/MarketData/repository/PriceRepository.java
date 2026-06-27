package org.application.mars.MarketData.repository;

import jakarta.transaction.Transactional;
import org.application.mars.MarketData.entities.PriceEntity;
import org.application.mars.MarketData.entities.TickerEntity;
import org.application.mars.MarketData.entities.id.PriceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, PriceId> {
    @Query("""
    SELECT p FROM PriceEntity p
    WHERE p.ticker.tickerId = :tickerId
    AND p.id.date BETWEEN :from AND :to
    ORDER BY p.id.date ASC
    """)
    List<PriceEntity> findPricesInRange(
            @Param("tickerId") Long tickerId,
            @Param("from") LocalDate from,
            @Param("to") LocalDate to
    );

    @Query("SELECT MAX(p.id.date) FROM PriceEntity p WHERE p.ticker = :ticker")
    LocalDate findLatestTradeDate(@Param("ticker") TickerEntity ticker);

    @Modifying
    @Query(value = """
    INSERT INTO price (ticker_id, trade_date, open, high, low, close, volume)
    SELECT
        UNNEST(:tickerIds),
        UNNEST(CAST(:dates AS date[])),
        UNNEST(:opens),
        UNNEST(:highs),
        UNNEST(:lows),
        UNNEST(:closes),
        UNNEST(CAST(:volumes AS bigint[]))
    ON CONFLICT (ticker_id, trade_date) DO NOTHING
    """, nativeQuery = true)
    void batchUpsert(
            @Param("tickerIds") Long[] tickerIds,
            @Param("dates")     String[] dates,
            @Param("opens")     BigDecimal[] opens,
            @Param("highs")     BigDecimal[] highs,
            @Param("lows")      BigDecimal[] lows,
            @Param("closes")    BigDecimal[] closes,
            @Param("volumes")   Long[] volumes
    );
}
