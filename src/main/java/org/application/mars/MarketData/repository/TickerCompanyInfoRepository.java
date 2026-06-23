package org.application.mars.MarketData.repository;

import org.application.mars.MarketData.entities.TickerCompanyInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TickerCompanyInfoRepository extends JpaRepository<TickerCompanyInfoEntity, Long> {
    //findById() and save() are already inherited
}
