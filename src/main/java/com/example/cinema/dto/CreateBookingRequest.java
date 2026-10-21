package com.example.cinema.dto;

import java.util.List;

public record CreateBookingRequest(
        Long customerId,
        Long showtimeId,
        List<Long> seatIds
) {
}
