package com.example.librarybookingsystem.service;

import com.example.librarybookingsystem.entity.Book;
import com.example.librarybookingsystem.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    public Book getBookById(Long id){
        return bookRepository.findById(id).orElseThrow(() ->new RuntimeException("Book not found"));
    }

    public List<Book> searchBooks(String keyword){
        return bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrCategoryContainingIgnoreCase(keyword,keyword,keyword);
    }
    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }
}
