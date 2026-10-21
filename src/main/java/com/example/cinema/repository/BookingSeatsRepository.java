package com.example.cinema.repository;

import com.example.cinema.entity.BookingSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingSeatsRepository extends JpaRepository<BookingSeat,Long> {


}
