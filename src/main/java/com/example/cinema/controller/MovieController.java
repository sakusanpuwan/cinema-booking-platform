package com.example.cinema.controller;

import com.example.cinema.dto.MovieDto;
import com.example.cinema.service.MovieService;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@Validated
@RequestMapping("/movies")
public class MovieController {

    // TODO: OpenAPI doc
    // TODO: @Transactional
    // TODO: Testing @DataJpaTest, @WebMvcTest, MockMvs, PostgreSQL Testcontainer

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping()
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        List<MovieDto> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable Long id) {
        MovieDto movie = movieService.getMovieByMovieId(id);
        return ResponseEntity.ok(movie);
    }

    @GetMapping(path = "/search")
    public ResponseEntity<List<MovieDto>> searchMovies(@RequestParam(required = false) @Size(max = 100, message = "Title must be at most 100 characters") String title,
                                                       @RequestParam(required = false) @Size(max = 50, message = "Certificate must be at most 50 characters") String certificate,
                                                       @RequestParam(required = false) @Size(max = 100, message = "Director must be at most 100 characters") String director,
                                                       @RequestParam(required = false) @DecimalMin(value = "0", message = "IMDB rating must be a positive number") @DecimalMax(value = "10", message = "IMDB rating must be between 0 and 10") BigDecimal minImdbRating,
                                                       @RequestParam(required = false) @Min(value = (short) 1888, message = "From year must be a valid year") Short fromYear,
                                                       @RequestParam(required = false) @Min(value = (short) 2026, message = "To year must be a valid year") Short toYear) {
        List<MovieDto> movies = movieService.searchMovies(title, certificate, director, minImdbRating, fromYear, toYear);
        return ResponseEntity.ok(movies);
    }

    @GetMapping(path = "/page/{page}")
    public ResponseEntity<List<MovieDto>> getMoviesPage(@PathVariable int page) {
        List<MovieDto> movies = movieService.getMoviesByPage(page, 10);
        return ResponseEntity.ok(movies);
    }

    @GetMapping(path = "/sort/page/{page}")
    public ResponseEntity<List<MovieDto>> sortMovies(@PathVariable int page, @RequestParam String filter, @RequestParam(required = false, defaultValue = "ASC") String dir) {
        List<MovieDto> movies = movieService.sortMoviesBy(filter, page, 10, dir);
        return ResponseEntity.ok(movies);
    }
}
