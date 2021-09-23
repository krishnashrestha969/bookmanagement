package com.bookmanagementapp.demo.service;

import com.bookmanagementapp.demo.repository.BookRepo;
import com.bookmanagementapp.demo.repository.BookingsRepo;
import com.bookmanagementapp.demo.model.Bookings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@Slf4j
public class BookingsServiceImpl implements BookingsService{
    @Autowired
    BookingsRepo bookingsRepo;
    @Autowired
    BookService bookService;
    @Autowired
    BookingsService bookingsService;
    @Autowired
    UserServiceImpl userServiceImpl;
    @Autowired
    BookRepo bookRepo;

    @Override
    public void createBookings(Bookings bookings,Long id) {
        LocalDateTime localDateTime = LocalDateTime.now();
         Long uid=userServiceImpl.getCurrentUserId();
         String bookName=bookRepo.searchNameOfBookById(id);
         String username=userServiceImpl.getCurrentUserName();
         String phoneNumber=userServiceImpl.getCurrentPhoneNumber();
         Bookings bookings1=new Bookings(bookings.getBookingId(),id,bookName,uid,username,phoneNumber,localDateTime);
//       bookingsRepo.save(bookings1);
         log.info("Booking has been Created "+bookingsRepo.save(bookings1));
    }

    @Override
    public boolean deleteBookings(Long id) {

    bookingsRepo.deleteById(id);
    log.info("Booking has been deleted by id"+id);
        return false;

    }

    @Override
    public List<Bookings> findAllBookings() {
        log.info("All the booking was shown");
        return bookingsRepo.findAll();

    }

    //For Normal User


    @Override
    public List<Bookings> getSingleUserBookings() {
        Long userId= userServiceImpl.getCurrentUserId();
        log.info("User booking was searched for id"+userId);
        return bookingsRepo.findSingleUserBookings(userId);

    }
}

