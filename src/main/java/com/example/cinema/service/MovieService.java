package com.example.cinema.service;

import com.example.cinema.dto.MovieDto;
import com.example.cinema.entity.Movie;
import com.example.cinema.exception.EntityNotFoundException;
import com.example.cinema.repository.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieDto> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream().map(MovieDto::getMovieDto).toList();
    }

    public MovieDto getMovieByMovieId(Long movieId) throws EntityNotFoundException {
        Optional<Movie> movie = movieRepository.findByMovieId(movieId);
        return movie.map(MovieDto::getMovieDto).orElseThrow(() -> new EntityNotFoundException("Movie not found with ID: " + movieId));
    }

    public List<MovieDto> searchMovies(String title, String certificate, String director, BigDecimal minImdbRating, Short fromYear, Short toYear) {
        Specification<Movie> spec = (root, query, cb) -> cb.conjunction();

        if (title != null && !title.isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%"));
        }

        // % = wildcard
        // %value% = match any value containing value m
        // value% = starts with value
        // %value = ends with value

        if (director != null && !director.isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("director")), "%" + director.toLowerCase() + "%"));
        }

        if (certificate != null && !certificate.isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(cb.lower(root.get("certificate")), certificate.toLowerCase()));
        }

        if (minImdbRating != null) {
            spec = spec.and((root, query, cb) ->
                    cb.greaterThanOrEqualTo(root.get("imdbRating"), minImdbRating));
        }

        if (fromYear != null) {
            spec = spec.and((root, query, cb) ->
                    cb.greaterThanOrEqualTo(root.get("releasedYear"), fromYear));
        }

        if (toYear != null) {
            spec = spec.and((root, query, cb) ->
                    cb.lessThanOrEqualTo(root.get("releasedYear"), toYear));
        }

        return movieRepository.findAll(spec).stream().map(MovieDto::getMovieDto).toList();
    }

    public List<MovieDto> getMoviesByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Movie> movies = movieRepository.findAll(pageable);
        return movies.getContent().stream().map(MovieDto::getMovieDto).toList();
    }

    public List<MovieDto> sortMoviesBy(String sortBy, int page, int size, String dir) {
        List<String> allowed = List.of("imdbRating","releasedYear");
        if (!allowed.contains(sortBy)) throw new IllegalArgumentException("Invalid sort field");

        Sort sort = Sort.by(Sort.Direction.fromString(dir == null ? "ASC" : dir), sortBy);
        Page<Movie> movies = movieRepository.findAll(PageRequest.of(page, size, sort));
        return movies.getContent().stream().map(MovieDto::getMovieDto).toList();
    }
}
