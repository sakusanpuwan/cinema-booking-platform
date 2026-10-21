package com.example.cinema.dto;

import com.example.cinema.entity.Booking;
import com.example.cinema.enums.BookingStatus;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record BookingDto(
        Long bookingId,
        Long customerId,
        String customerName,
        Long showtimeId,
        BookingStatus status,
        BigDecimal totalAmount,
        OffsetDateTime expiresAt,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
    public BookingDto(Booking booking) {
        this(
                booking.getBookingId(),
                booking.getCustomer().getCustomerId(),
                booking.getCustomer().getFirstName() + " " + booking.getCustomer().getLastName(),
                booking.getShowtime().getShowtimeId(),
                booking.getStatus(),
                booking.getTotalAmount(),
                booking.getExpiresAt(),
                booking.getCreatedAt(),
                booking.getUpdatedAt()
        );
    }
}