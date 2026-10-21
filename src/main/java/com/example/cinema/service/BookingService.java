package com.example.cinema.service;

import com.example.cinema.dto.BookingDto;
import com.example.cinema.dto.BookingResponse;
import com.example.cinema.dto.CreateBookingRequest;
import com.example.cinema.dto.ShowtimeSeatsDto;
import com.example.cinema.entity.*;
import com.example.cinema.enums.BookingStatus;
import com.example.cinema.enums.SeatStatus;
import com.example.cinema.exception.BookingException;
import com.example.cinema.exception.EntityNotFoundException;
import com.example.cinema.repository.BookingRepository;
import com.example.cinema.repository.BookingSeatsRepository;
import com.example.cinema.repository.ShowtimeRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BookingService {

    private BookingRepository bookingRepository;
    private ShowtimeRepository showtimeRepository;
    private BookingSeatsRepository bookingSeatsRepository;
    private EntityManager entityManager;

    public BookingService(BookingRepository bookingRepository, ShowtimeRepository showtimeRepository, BookingSeatsRepository bookingSeatsRepository, EntityManager entityManager) {
        this.bookingRepository = bookingRepository;
        this.showtimeRepository = showtimeRepository;
        this.bookingSeatsRepository = bookingSeatsRepository;
        this.entityManager = entityManager;
    }

    public List<BookingDto> getAllBookings() {
        return bookingRepository.findAll().stream().map(BookingDto::new).toList();
    }

    public BookingResponse createBooking(CreateBookingRequest bookingRequest) {
        try {
            List<ShowtimeSeatsDto> showtimeSeats = showtimeRepository.findShowtimeSeatsByShowtimeId(bookingRequest.showtimeId());

            if (showtimeSeats.isEmpty()) {
                throw new EntityNotFoundException("Showtime not found");
            }

            for (Long seatId : bookingRequest.seatIds()) {
                boolean seatAvailable = showtimeSeats.stream()
                        .anyMatch(seat -> seat.getSeatId().equals(seatId) && seat.getStatus().equals(SeatStatus.AVAILABLE));

                if (!seatAvailable) {
                    throw new IllegalArgumentException("Seat with ID " + seatId + " is not available for booking.");
                }
            }

            Booking booking = new Booking();
            booking.setShowtime(entityManager.getReference(Showtime.class, bookingRequest.showtimeId()));
            booking.setCustomer(entityManager.getReference(Customer.class, bookingRequest.customerId()));
            booking.setStatus(BookingStatus.CONFIRMED);
            booking.setTotalAmount(BigDecimal.valueOf(10l * bookingRequest.seatIds().size()));
            Booking savedBooking = bookingRepository.save(booking);

            for (Long seatId : bookingRequest.seatIds()) {
                BookingSeat bookingSeat = new BookingSeat();
                bookingSeat.setBooking(savedBooking);
                bookingSeat.setShowtime(entityManager.getReference(Showtime.class, bookingRequest.showtimeId()));
                bookingSeat.setSeat(entityManager.getReference(Seat.class, seatId));
                bookingSeat.setPrice(BigDecimal.valueOf(10l));
                BookingSeat savedBookingSeat = bookingSeatsRepository.save(bookingSeat);
            }

            return new BookingResponse(savedBooking.getBookingId(), bookingRequest.customerId(), bookingRequest.showtimeId(), savedBooking.getTotalAmount(), bookingRequest.seatIds());
        } catch (Exception e) {
            throw new BookingException("Error occurred while creating booking: " + e.getMessage());
        }

    }
}
