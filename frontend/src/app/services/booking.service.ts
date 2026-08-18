import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class BookingService {

  private apiUrl = 'http://localhost:8080/api/bookings';

  constructor(private http: HttpClient) {}

  createBooking(booking: any) {
    return this.http.post(this.apiUrl, booking);
  }

  getBookings() {
    return this.http.get(this.apiUrl);
  }

  getBookingsByUser(userId: number) {
    return this.http.get(`${this.apiUrl}/user/${userId}`);
  }

  returnBook(id: number) {
    return this.http.put(`${this.apiUrl}/${id}/return`, {});
  }
}
