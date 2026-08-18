import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BookService } from '../../services/book.service';

@Component({
  selector: 'app-admin-books',
  imports: [CommonModule, FormsModule],
  templateUrl: './admin-books.html',
  styleUrl: './admin-books.css'
})
export class AdminBooks implements OnInit {

  books: any[] = [];

  book = {
    title: '',
    author: '',
    category: '',
    available: true
  };

  constructor(private bookService: BookService) {}

  ngOnInit(): void {
    this.loadBooks();
  }

  loadBooks(): void {
    this.bookService.getBooks().subscribe({
      next: (response: any) => {
        console.log('Admin books loaded:', response);
        this.books = response;
      },
      error: (error: any) => {
        console.error('Failed to load books:', error);
      }
    });
  }

  addBook(): void {
    this.bookService.addBook(this.book).subscribe({
      next: () => {
        alert('Book added successfully');
        this.book = {
          title: '',
          author: '',
          category: '',
          available: true
        };
        this.loadBooks();
      },
      error: (error: any) => {
        console.error('Failed to add book:', error);
        alert('Failed to add book');
      }
    });
  }
}
