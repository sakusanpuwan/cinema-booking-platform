package com.example.cinema.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "cinemas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cinema_id", nullable = false, updatable = false)
    private Long cinemaId;

    @Column(name = "name", length = 150, nullable = false)
    private String name;

    @Column(name = "address_line1", length = 255, nullable = false)
    private String addressLine1;

    @Column(name = "address_line2", length = 255)
    private String addressLine2;

    @Column(name = "city", length = 100, nullable = false)
    private String city;

    @Column(name = "postcode", length = 20)
    private String postcode;

    @Column(name = "country", length = 100, nullable = false)
    private String country;

    @Column(name = "phone", length = 30)
    private String phone;

    @Column(name = "timezone", length = 50, nullable = false, columnDefinition = "VARCHAR(50) default 'Europe/London'")
    private String timezone = "Europe/London";

    @Column(name = "active", nullable = false, columnDefinition = "BOOLEAN default true")
    private Boolean active = true;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime updatedAt;

    @OneToMany(mappedBy = "cinema")
    List<Screen> screens;
}