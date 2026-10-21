package com.example.cinema.dto;

import com.example.cinema.enums.BookingStatus;
import com.example.cinema.enums.SeatStatus;
import lombok.*;

@Getter
@Setter
public class ShowtimeSeatsDto {
    private Long seatId;
    private String rowLabel;
    private Short seatNumber;
    private SeatStatus status;

    public ShowtimeSeatsDto(
            Long seatId,
            String rowLabel,
            Short seatNumber,
            BookingStatus bookingStatus
    ) {
        this.seatId = seatId;
        this.rowLabel = rowLabel;
        this.seatNumber = seatNumber;
        this.status = mapBookingStatusToSeatStatus(bookingStatus);
    }


    private static SeatStatus mapBookingStatusToSeatStatus(BookingStatus bookingStatus) {
        if (bookingStatus == null) return SeatStatus.AVAILABLE;

        return switch (bookingStatus) {
            case PENDING -> SeatStatus.RESERVED;
            case CONFIRMED -> SeatStatus.BOOKED;
            case CANCELLED -> SeatStatus.AVAILABLE;
        };
    }
}
