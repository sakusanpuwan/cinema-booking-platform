package com.example.cinema.entity;

import com.example.cinema.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id", nullable = false, updatable = false)
    private Long bookingId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false, foreignKey = @ForeignKey(name = "fk_bookings_customer"))
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "showtime_id", nullable = false, foreignKey = @ForeignKey(name = "fk_bookings_showtime"))
    private Showtime showtime;

    @Column(name = "status", length = 20, nullable = false, columnDefinition = "VARCHAR(20) default 'PENDING'")
    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @Column(name = "total_amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "expires_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime expiresAt;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime updatedAt;
}