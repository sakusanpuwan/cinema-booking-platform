package com.example.cinema.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Entity
@Table(name = "seats",
        uniqueConstraints = @UniqueConstraint(name = "uk_seat_screen_position",
                columnNames = {"screen_id", "row_label", "seat_number"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id", nullable = false, updatable = false)
    private Long seatId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "screen_id", nullable = false, foreignKey = @ForeignKey(name = "fk_seats_screen"))
    private Screen screen;

    @Column(name = "row_label", length = 5, nullable = false)
    private String rowLabel;

    @Column(name = "seat_number", nullable = false)
    private Short seatNumber;

    @Column(name = "seat_type", length = 30, nullable = false, columnDefinition = "VARCHAR(30) default 'STANDARD'")
    private String seatType = "STANDARD";

    @Column(name = "active", nullable = false, columnDefinition = "BOOLEAN default true")
    private Boolean active = true;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime updatedAt;
}