import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BookService } from '../../services/book.service';

@Component({
  selector: 'app-books',
  imports: [CommonModule],
  templateUrl: './books.html',
  styleUrl: './books.css'
})
export class Books implements OnInit {

  books: any[] = [];

  constructor(private bookService: BookService) {}

  ngOnInit(): void {
    this.loadBooks();
  }

  loadBooks(): void {
    this.bookService.getBooks().subscribe({
      next: (response: any) => {
        console.log('Books loaded:', response);
        this.books = response;
      },
      error: (error) => {
        console.error('Failed to load books:', error);
      }
    });
  }

  bookNow(book: any): void {
    console.log('Book selected:', book);
  }
}
