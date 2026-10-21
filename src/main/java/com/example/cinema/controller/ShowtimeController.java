package com.example.cinema.controller;

import com.example.cinema.dto.ShowtimeDto;
import com.example.cinema.dto.ShowtimeSeatsDto;
import com.example.cinema.service.ShowtimeService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
@RequestMapping("/showtimes")
public class ShowtimeController {

    private ShowtimeService showtimeService;

    public ShowtimeController(ShowtimeService showtimeService) {
        this.showtimeService = showtimeService;
    }

    @GetMapping()
    public ResponseEntity<List<ShowtimeDto>> getAllShowtimes() {
        List<ShowtimeDto> showtimes = showtimeService.getAllShowtimes();
        return ResponseEntity.ok(showtimes);
    }

    @GetMapping(path = "/{showtimeId}")
    public ResponseEntity<ShowtimeDto> getShowtimeById(@PathVariable Long showtimeId) {
        ShowtimeDto showtime = showtimeService.getShowtimeById(showtimeId);
        return ResponseEntity.ok(showtime);
    }

    @GetMapping(path = "/{showtimeId}/seats")
    public ResponseEntity<List<ShowtimeSeatsDto>> getShowtimeSeats(@PathVariable Long showtimeId) {
        List<ShowtimeSeatsDto> seats = showtimeService.getShowtimeSeats(showtimeId);
        return ResponseEntity.ok(seats);
    }
}
