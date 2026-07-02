package org.application.mars.MarketData.components;

import lombok.RequiredArgsConstructor;
import org.application.mars.MarketData.service.HolidayService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HolidayListener {
    private final HolidayService holidayService;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        holidayService.refreshHolidays();
    }
}
