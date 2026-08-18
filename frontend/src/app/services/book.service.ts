import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  private apiUrl = 'http://localhost:8080/api/books';

  constructor(private http: HttpClient) {
  }

  // GET ALL BOOKS
  getBooks() {
    return this.http.get(this.apiUrl);
  }

  // ADD BOOK - ADMIN
  addBook(book: any) {
    return this.http.post(this.apiUrl, book);
  }
}
