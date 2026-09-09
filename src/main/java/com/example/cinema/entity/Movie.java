package com.example.cinema.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "movies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id", nullable = false, updatable = false)
    private Long movieId;

    @Column(name = "poster_link", length = 500)
    private String posterLink;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "released_year")
    private Short releasedYear;

    @Column(name = "certificate", length = 20)
    private String certificate;

    @Column(name = "runtime_minutes", length = 20)
    private String runtimeMinutes;

    @Column(name = "genre")
    private String genre;

    @Column(name = "imdb_rating", precision = 3, scale = 1)
    private BigDecimal imdbRating;

    @Column(name = "overview", columnDefinition = "TEXT")
    private String overview;

    @Column(name = "meta_rating")
    private Short metaRating;

    @Column(name = "director")
    private String director;

    @Column(name = "star1")
    private String star1;

    @Column(name = "star2")
    private String star2;

    @Column(name = "star3")
    private String star3;

    @Column(name = "star4")
    private String star4;

    @Column(name = "no_of_votes")
    private Integer noOfVotes;

    @Column(name = "gross", precision = 15, scale = 2)
    private BigDecimal gross;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime updatedAt;
}