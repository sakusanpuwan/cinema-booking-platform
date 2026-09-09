package com.example.cinema.service;

import com.example.cinema.dto.MovieDto;
import com.example.cinema.entity.Movie;
import com.example.cinema.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
