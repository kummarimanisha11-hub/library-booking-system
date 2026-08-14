package com.example.librarybookingsystem.controller;

import com.example.librarybookingsystem.entity.Booking;
import com.example.librarybookingsystem.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingService.saveBooking(booking);
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }
    // USER RESERVATION HISTORY
    @GetMapping("/user/{userId}")
    public List<Booking> getBookingsByUserId(@PathVariable Long userId) {
        return bookingService.getBookingsByUserId(userId);
    }
    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Long id){
        return bookingService.getBookingById(id);
    }

    @PutMapping("/{id}")
    public Booking updateBooking(@PathVariable Long id,
                                 @RequestBody Booking booking) {

        Booking existingBooking = bookingService.getAllBookings()
                .stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        existingBooking.setUserId(booking.getUserId());
        existingBooking.setBookId(booking.getBookId());
        existingBooking.setBookingDate(booking.getBookingDate());
        existingBooking.setReturnDate(booking.getReturnDate());
        existingBooking.setStatus(booking.getStatus());

        return bookingService.saveBooking(existingBooking);
    }
    // RETURN BOOK
    @PutMapping("/{id}/return")
    public Booking returnBook(@PathVariable Long id) {
        return bookingService.returnBook(id);
    }

    @DeleteMapping("/{id}")
    public String deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return "Booking deleted successfully";
    }
}
