package com.example.cinema.service;

import com.example.cinema.dto.BookingDto;
import com.example.cinema.dto.ShowtimeDto;
import com.example.cinema.exception.EntityNotFoundException;
import com.example.cinema.repository.BookingRepository;
import com.example.cinema.repository.ShowtimeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<BookingDto> getAllBookings() {
        return bookingRepository.findAll().stream().map(BookingDto::new).toList();
    }
}
