package com.example.bookstorageservice.Service;

import com.example.bookstorageservice.DTO.BookDto;

import java.util.List;

public interface BookService {
    BookDto addBook(BookDto bookDto);
    List<BookDto> getAllBooks();
    BookDto getBookById(Long id);
    BookDto getBookByIsbn(String isbn);
    BookDto updateBook(BookDto bookDto);
    void deleteBook(Long id);
}
