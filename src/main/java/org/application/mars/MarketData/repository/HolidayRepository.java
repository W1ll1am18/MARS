package org.application.mars.MarketData.repository;

import org.application.mars.MarketData.entities.MarketHolidayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface HolidayRepository extends JpaRepository<MarketHolidayEntity, Long> {
    @Query("SELECT MAX(h.holidayDate) FROM MarketHolidayEntity h")
    LocalDate findLatestHolidayDate();
    Optional<MarketHolidayEntity> findByHolidayDate(LocalDate date);
}
