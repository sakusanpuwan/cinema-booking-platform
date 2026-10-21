package com.example.cinema.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "booking_seats", uniqueConstraints = {@UniqueConstraint(name = "uk_booking_seats_showtime_seat", columnNames = {"showtime_id", "seat_id"}), @UniqueConstraint(name = "uk_booking_seats_booking_seat", columnNames = {"booking_id", "seat_id"})}, indexes = {@Index(name = "idx_booking_seats_booking", columnList = "booking_id"), @Index(name = "idx_booking_seats_seat", columnList = "seat_id")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_seat_id", nullable = false, updatable = false)
    private Long bookingSeatId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "booking_id", nullable = false, foreignKey = @ForeignKey(name = "fk_booking_seats_booking"))
    private Booking booking;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "showtime_id", nullable = false, foreignKey = @ForeignKey(name = "fk_booking_seats_showtime"))
    private Showtime showtime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "seat_id", nullable = false, foreignKey = @ForeignKey(name = "fk_booking_seats_seat"))
    private Seat seat;

    @Column(name = "price", precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime createdAt;
}