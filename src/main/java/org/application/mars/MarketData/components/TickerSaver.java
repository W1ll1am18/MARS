package org.application.mars.MarketData.components;

import lombok.RequiredArgsConstructor;
import org.application.mars.MarketData.entities.TickerEntity;
import org.application.mars.MarketData.repository.TickerRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

// New bean, its sole job is saving in an isolated transaction
@Component
@RequiredArgsConstructor
public class TickerSaver {
    private final TickerRepository tickerRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Optional<TickerEntity> save(TickerEntity entity) {
        return Optional.of(tickerRepository.save(entity));
    }
}