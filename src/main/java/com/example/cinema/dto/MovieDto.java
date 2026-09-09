package com.example.cinema.dto;

import com.example.cinema.entity.Movie;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieDto {
    private String posterLink;
    private String title;
    private Short releasedYear;
    private String certificate;
    private String runtimeMinutes;
    private String genre;
    private BigDecimal imdbRating;
    private String overview;
    private Short metaRating;
    private String director;
    private String star1;
    private String star2;
    private String star3;
    private String star4;
    private Integer noOfVotes;
    private BigDecimal gross;

    public static MovieDto getMovieDto(Movie movie) {
        return MovieDto.builder()
                .title(movie.getTitle())
                .posterLink(movie.getPosterLink())
                .releasedYear(movie.getReleasedYear())
                .certificate(movie.getCertificate())
                .runtimeMinutes(movie.getRuntimeMinutes())
                .genre(movie.getGenre())
                .imdbRating(movie.getImdbRating())
                .overview(movie.getOverview())
                .metaRating(movie.getMetaRating())
                .director(movie.getDirector())
                .star1(movie.getStar1())
                .star2(movie.getStar2())
                .star3(movie.getStar3())
                .star4(movie.getStar4())
                .noOfVotes(movie.getNoOfVotes())
                .gross(movie.getGross())
                .build();
    }
}
