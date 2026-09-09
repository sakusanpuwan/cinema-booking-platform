package com.example.cinema.controller;

import com.example.cinema.dto.MovieDto;
import com.example.cinema.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping()
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        List<MovieDto> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }
}
