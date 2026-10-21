package com.example.cinema.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record ShowtimeDto(
        Long showtimeId,

        Long movieId,
        String title,
        String runtimeMinutes,

        Long screenId,
        Short screenNumber,
        Integer totalSeats,

        Long cinemaId,
        String name,

        BigDecimal basePrice,
        OffsetDateTime startsAt,
        OffsetDateTime endsAt,
        String status
) {
}
