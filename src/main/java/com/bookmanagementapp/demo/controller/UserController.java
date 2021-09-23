package com.bookmanagementapp.demo.controller;

import com.bookmanagementapp.demo.model.Bookings;
import com.bookmanagementapp.demo.model.User;
import com.bookmanagementapp.demo.service.BookService;
import com.bookmanagementapp.demo.service.BookTypeService;
import com.bookmanagementapp.demo.service.BookingsService;
import com.bookmanagementapp.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller

public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookTypeService bookTypeService;

    @Autowired
    private BookingsService bookingsService;

    //for USER Registration and login
    @GetMapping(value = "/loginIndex")
    public String firstindex(Model model, @Param("name") String name) {
        model.addAttribute("bookTypeList",bookTypeService.findBookTypeList());
        model.addAttribute("bookList",bookService.findAllBook(name));
        model.addAttribute("name",name);
        return "loginIndex";
    }

    @GetMapping("/user/register")
    public String registerPage() {
        return "userRegister";
    }

    @PostMapping("/user/register")
    public String registerUser(@ModelAttribute User user){
        userService.registerUser(user);
//        userService.createRoleUser(user);
        return "redirect:/user/login";
    }

    @GetMapping("/user/login")
    public String LoginPage() {
        return "/login";
    }

//    -------------------------------------------------------------------------------------------------------------------------------

    //For Bookings of User
    @GetMapping("/user/createbooking/{id}")     // For user to create a booking
    public String makeABooking(@PathVariable("id") Long id,Bookings bookings, Model model)
    {
        bookingsService.createBookings(bookings ,id);
         return "redirect:/loginIndex";
    }

    @GetMapping("/user/deletebooking/{id}")     //For user to delete the booking
    public String deleteBooking(@PathVariable("id") Long id)
    {
        bookingsService.deleteBookings(id);
        return "redirect:/loginIndex";
    }

    @GetMapping("/user/mybookings")
    public String getMyBookings(Model model)
    {
        System.out.println(bookingsService.getSingleUserBookings().toString());
        model.addAttribute("userbooking", bookingsService.getSingleUserBookings());
        return "/mybookings";

    }

//----------------------------------------------------------------------------------------------------------------------------------------

    //For admin
    @GetMapping("/admin/bookings")       //For admin to see all bookings by Admin
    public String getAllBookings(Model model) {
       model.addAttribute("allBookings",bookingsService.findAllBookings());
       return "/bookings";
    }








//--------------------------------------------------------------------------------------------------------------------------------
    //REST API Testing For Bookings of User
//    @GetMapping("/user/createbooking/{id}")
//    public ResponseEntity<?> makeABooking(@PathVariable("id") Long id,Bookings bookings)
//    {
//        bookingsService.createBookings(bookings ,id);
//         return new ResponseEntity<>("Booking is done",null, HttpStatus.OK);
//
//    }
//
//    @GetMapping("/user/bookings")
//    public ResponseEntity<?> getAllBookings() {
//        List<Bookings> bookings = bookingsService.findAllBookings();
//        if (bookings != null) {
//            return new ResponseEntity<>(bookings, null, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("No Bookings Found", null, HttpStatus.OK);
//
//        }
//    }
//
//        @GetMapping("/user/bookings/delete/{id}")
//        public ResponseEntity<?> deleteBooking(@PathVariable("id") Long id){
//            if(bookingsService.deleteBookings(id)) {
//            return new ResponseEntity<>("No records found to delete",null,HttpStatus.OK);
//            }
//            else {
//                return new ResponseEntity<>("Deleted",null,HttpStatus.OK);
//            }
//        }


        @GetMapping("/mybookings")
        public ResponseEntity<?> getuserBookings()
        {
            if(bookingsService.getSingleUserBookings()!=null) {
            return new ResponseEntity<>(bookingsService.getSingleUserBookings(),null,HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("nothing found",null,HttpStatus.OK);
            }
//
//
//
//        }


}}











