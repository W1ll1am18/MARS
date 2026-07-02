package org.application.mars.MarketData.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "market_holiday")
public class MarketHolidayEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "holiday_id")
    private Long holidayId;
    private String exchange;

    @Column(name = "holiday_date")
    private LocalDate holidayDate;
    private String name;
    private String status;

    @Column(name = "open_time")
    private Instant openTime;

    @Column(name = "close_time")
    private Instant closeTime;
}
