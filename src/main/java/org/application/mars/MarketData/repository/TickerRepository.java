package org.application.mars.MarketData.repository;

import org.application.mars.MarketData.models.Massive.enums.Locale;
import org.application.mars.MarketData.models.Massive.enums.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.application.mars.MarketData.entities.TickerEntity;

import java.util.Optional;

@Repository
public interface TickerRepository extends JpaRepository<TickerEntity, Long> {
    Optional<TickerEntity> findByTicker(String ticker);
    Optional<TickerEntity> findByCompositeFigi(String compositeFigi);
    Optional<TickerEntity> findByTickerAndPrimaryExchange(String ticker, String primaryExchange);
    Optional<TickerEntity> findByTickerAndMarketAndLocale(String ticker, Market market, Locale locale);
}