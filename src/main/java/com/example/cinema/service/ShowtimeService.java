package com.example.cinema.service;

import com.example.cinema.dto.ShowtimeDto;
import com.example.cinema.dto.ShowtimeSeatsDto;
import com.example.cinema.exception.EntityNotFoundException;
import com.example.cinema.repository.ShowtimeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowtimeService {

    private ShowtimeRepository showtimeRepository;

    public ShowtimeService(ShowtimeRepository showtimeRepository) {
        this.showtimeRepository = showtimeRepository;
    }

    public List<ShowtimeDto> getAllShowtimes() {
        return showtimeRepository.findAllShowtimes();
    }

    public ShowtimeDto getShowtimeById(Long showtimeId) {
        return showtimeRepository.findShowtimeById(showtimeId)
                .orElseThrow(() -> new EntityNotFoundException("Showtime not found with ID: " + showtimeId));
    }

    public List<ShowtimeSeatsDto> getShowtimeSeats(Long showtimeId) {
        return showtimeRepository.findShowtimeSeatsByShowtimeId(showtimeId);
    }
}
