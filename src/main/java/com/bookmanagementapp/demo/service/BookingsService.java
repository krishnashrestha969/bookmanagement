package com.bookmanagementapp.demo.service;

import com.bookmanagementapp.demo.model.Bookings;

import java.util.List;

public interface BookingsService {
    void createBookings(Bookings bookings,Long id);
    boolean deleteBookings(Long id);
    List<Bookings> findAllBookings();
    List<Bookings> getSingleUserBookings();
}
