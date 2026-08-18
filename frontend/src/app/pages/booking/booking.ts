import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BookingService } from '../../services/booking.service';

@Component({
  selector: 'app-booking',
  imports: [CommonModule, FormsModule],
  templateUrl: './booking.html',
  styleUrl: './booking.css'
})
export class Booking {

  booking = {
    userId: null,
    bookId: null,
    bookingDate: '',
    returnDate: '',
    status: 'BOOKED'
  };

  constructor(private bookingService: BookingService) {}

  confirmBooking(): void {

    console.log('Booking data:', this.booking);

    this.bookingService.createBooking(this.booking).subscribe({
      next: (response) => {
        console.log('Booking successful:', response);
        alert('Booking successful!');
      },
      error: (error) => {
        console.error('Booking failed:', error);
        alert('Booking failed!');
      }
    });
  }
}
