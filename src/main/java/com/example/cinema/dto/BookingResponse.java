package com.example.cinema.dto;

import java.math.BigDecimal;
import java.util.List;

public record BookingResponse(
        Long bookingId,
        Long customerId,
        Long showtimeId,
        BigDecimal totalAmount,
        List<Long> seatIds
) {}
