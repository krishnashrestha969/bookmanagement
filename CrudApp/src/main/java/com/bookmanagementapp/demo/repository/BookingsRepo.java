package com.bookmanagementapp.demo.repository;

import com.bookmanagementapp.demo.model.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingsRepo extends JpaRepository <Bookings,Long> {

    @Query(value = "SELECT * From bookings where user_id=?", nativeQuery = true)
    List<Bookings> findSingleUserBookings(Long id);

}
