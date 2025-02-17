package com.example.demo1.Service;

import com.example.demo1.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {
        BookDto addBook(BookDto bookDto);
    List<BookDto> getAllBooks();
    BookDto getBookById(Long id);
    BookDto getBookByIsbn(String isbn);
    BookDto updateBook(BookDto bookDto);
    void deleteBook(Long id);
}
