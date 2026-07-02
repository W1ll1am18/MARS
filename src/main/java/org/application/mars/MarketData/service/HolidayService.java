package org.application.mars.MarketData.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.application.mars.MarketData.client.MassiveClient;
import org.application.mars.MarketData.entities.MarketHolidayEntity;
import org.application.mars.MarketData.models.Massive.Operations.MarketHoliday;
import org.application.mars.MarketData.repository.HolidayRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class HolidayService {
    private final MassiveClient massiveClient;
    private final HolidayRepository holidayRepository;

    public void refreshHolidays() {
        LocalDate latestHolidayDate = holidayRepository.findLatestHolidayDate();
        //If holiday exists check if after
        if (latestHolidayDate != null && latestHolidayDate.isBefore(LocalDate.now())) {
            saveHolidays();
        }

        if (latestHolidayDate == null) {
            saveHolidays();
        }
    }

    private void saveHolidays() {
        List<MarketHoliday> marketHolidays = massiveClient.getMarketHolidays();

        if (marketHolidays != null) {
            ArrayList<MarketHolidayEntity> holidays = new ArrayList<>();
            for (MarketHoliday marketHoliday : marketHolidays) {
                holidays.add(mapToEntity(marketHoliday));
            }
            holidayRepository.saveAll(holidays);
        }
    }

    private MarketHolidayEntity mapToEntity(MarketHoliday holiday) {
        MarketHolidayEntity entity = new MarketHolidayEntity();

        entity.setHolidayDate(
                LocalDate.parse(Objects.requireNonNull(holiday.getDate(), "Holiday date is required"))
        );

        entity.setExchange(
                Objects.requireNonNull(holiday.getExchange(), "Exchange is required")
        );

        entity.setStatus(
                Objects.requireNonNull(holiday.getStatus(), "Status is required")
        );

        if (holiday.getName() != null) {
            entity.setName(holiday.getName());
        }

        if (holiday.getOpen() != null) {
            entity.setOpenTime(Instant.parse(holiday.getOpen()));
        }

        if (holiday.getClose() != null) {
            entity.setCloseTime(Instant.parse(holiday.getClose()));
        }

        return entity;
    }
}
