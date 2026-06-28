package org.application.mars.MarketData.repository;

import org.application.mars.MarketData.entities.TickerFinancialsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TickerFinancialsRepository extends JpaRepository<TickerFinancialsEntity, Long> {
    @Query("""
        SELECT tf FROM TickerFinancialsEntity tf
        WHERE tf.ticker.ticker = :ticker
        """)
    Optional<TickerFinancialsEntity> findByTicker(@Param("ticker") String ticker);
}
