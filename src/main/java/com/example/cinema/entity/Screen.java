package com.example.cinema.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "screens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Screen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "screen_id", nullable = false, updatable = false)
    private Long screenId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cinema_id", nullable = false, foreignKey = @ForeignKey(name = "fk_screens_cinema"))
    private Cinema cinema;

    @OneToMany(mappedBy = "screen")
    private List<Seat> seats;

    @Column(name = "screen_number", nullable = false)
    private Short screenNumber;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "screen_type", length = 30, nullable = false)
    private String screenType;

    @Column(name = "total_seats", nullable = false)
    private Integer totalSeats;

    @Column(name = "active", nullable = false, columnDefinition = "BOOLEAN default true")
    private Boolean active = true;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime updatedAt;
}