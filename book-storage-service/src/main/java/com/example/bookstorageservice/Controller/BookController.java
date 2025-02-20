package com.example.bookstorageservice.Controller;

import com.example.bookstorageservice.Constants.HttpStatusConstants;
import com.example.bookstorageservice.Service.Impl.BookServiceImpl;
import com.example.bookstorageservice.DTO.BookDto;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class BookController {
    private final BookServiceImpl bookService;

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<BookDto> books = bookService.getAllBooks();
        return ResponseEntity.status(HttpStatusConstants.OK_STATUS).body(books);
    }

    @PostMapping("/addBook")
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto){
        BookDto createdBook = bookService.addBook(bookDto);
        return ResponseEntity.status(HttpStatusConstants.CREATED_STATUS).body(createdBook);
    }

    @GetMapping("/getBookById/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id){
        BookDto books = bookService.getBookById(id);
        return ResponseEntity.status(HttpStatusConstants.OK_STATUS).body(books);
    }

    @PutMapping("/updateBook")
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto){
        BookDto updateBook = bookService.updateBook(bookDto);
        return ResponseEntity.status(HttpStatusConstants.OK_STATUS).body(updateBook);
    }

    @GetMapping("/getBookByIsbn/{isbn}")
    public ResponseEntity<BookDto> getBookByIsbn (@PathVariable ("isbn") String isbn){
        BookDto books = bookService.getBookByIsbn(isbn);
        return ResponseEntity.status(HttpStatusConstants.OK_STATUS).body(books);
    }

    @DeleteMapping("/deleteBookById/{id}")
    public ResponseEntity<BookDto> deleteBookById(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatusConstants.NO_CONTENT_STATUS).build();
    }
}
