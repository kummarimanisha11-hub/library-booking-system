package com.example.librarybookingsystem.service;

import com.example.librarybookingsystem.entity.Booking;
import com.example.librarybookingsystem.entity.Book;
import com.example.librarybookingsystem.repository.BookingRepository;
import com.example.librarybookingsystem.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookRepository bookRepository;

    public BookingService(BookingRepository bookingRepository,
                          BookRepository bookRepository) {
        this.bookingRepository = bookingRepository;
        this.bookRepository = bookRepository;
    }

    public Booking saveBooking(Booking booking) {

        Book book = bookRepository.findById(booking.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (!book.isAvailable()) {
            throw new RuntimeException("Book is not available");
        }

        Booking savedBooking = bookingRepository.save(booking);

        book.setAvailable(false);
        bookRepository.save(book);

        return savedBooking;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    // CHECK BOOK AVAILABILITY
    public boolean isBookAvailable(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        return book.isAvailable();
    }

    // USER RESERVATION HISTORY
    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }
    // RETURN BOOK
    public Booking returnBook(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        Book book = bookRepository.findById(booking.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        booking.setStatus("RETURNED");
        book.setAvailable(true);

        bookRepository.save(book);

        return bookingRepository.save(booking);
    }

}