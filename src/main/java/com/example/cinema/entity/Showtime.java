package com.example.cinema.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "showtimes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Showtime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "showtime_id", nullable = false, updatable = false)
    private Long showtimeId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movie_id", nullable = false, foreignKey = @ForeignKey(name = "fk_showtimes_movie"))
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "screen_id", nullable = false, foreignKey = @ForeignKey(name = "fk_showtimes_screen"))
    private Screen screen;

    @Column(name = "base_price", precision = 10, scale = 2, nullable = false)
    private BigDecimal basePrice;

    @Column(name = "starts_at", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime startsAt;

    @Column(name = "ends_at", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime endsAt;

    @Column(name = "status", length = 30, nullable = false, columnDefinition = "VARCHAR(30) default 'SCHEDULED'")
    private String status = "SCHEDULED";

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime updatedAt;
}