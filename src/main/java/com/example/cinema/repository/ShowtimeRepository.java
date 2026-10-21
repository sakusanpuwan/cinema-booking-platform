package com.example.cinema.repository;

import com.example.cinema.dto.ShowtimeDto;
import com.example.cinema.dto.ShowtimeSeatsDto;
import com.example.cinema.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime,Long>, JpaSpecificationExecutor<Showtime> {

    @Query("""
    SELECT new com.example.cinema.dto.ShowtimeDto(
        s.showtimeId,
        m.movieId,
        m.title,
        m.runtimeMinutes,
        sc.screenId,
        sc.screenNumber,
        sc.totalSeats,
        c.cinemaId,
        c.name,
        s.basePrice,
        s.startsAt,
        s.endsAt,
        s.status
    )
    FROM Showtime s
    JOIN s.movie m
    JOIN s.screen sc
    JOIN sc.cinema c
    ORDER BY s.startsAt
    """)
    List<ShowtimeDto> findAllShowtimes();

    @Query("""
    SELECT new com.example.cinema.dto.ShowtimeDto(
        s.showtimeId,
        m.movieId,
        m.title,
        m.runtimeMinutes,
        sc.screenId,
        sc.screenNumber,
        sc.totalSeats,
        c.cinemaId,
        c.name,
        s.basePrice,
        s.startsAt,
        s.endsAt,
        s.status
    )
    FROM Showtime s
    JOIN s.movie m
    JOIN s.screen sc
    JOIN sc.cinema c
    WHERE s.showtimeId  = :showtimeId
    ORDER BY s.startsAt
    """)
    Optional<ShowtimeDto> findShowtimeById(Long showtimeId);

    @Query("""
    SELECT new com.example.cinema.dto.ShowtimeSeatsDto(
        seat.seatId,
        seat.rowLabel,
        seat.seatNumber,
        booking.status
    )
    FROM Showtime st
    JOIN st.screen screen
    JOIN screen.seats seat
    LEFT JOIN BookingSeat bs
        ON bs.showtime = st AND bs.seat = seat
    LEFT JOIN bs.booking booking
    WHERE st.showtimeId = :showtimeId
      AND seat.active = true
    ORDER BY seat.rowLabel, seat.seatNumber
    """)
    List<ShowtimeSeatsDto> findShowtimeSeatsByShowtimeId(Long showtimeId);
}
