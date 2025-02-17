package com.example.demo1.controller;

import com.example.demo1.Service.Impl.BookServiceImpl;
import com.example.demo1.dto.BookDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class BookController {
    private final BookServiceImpl bookService;

    @GetMapping
    public List<BookDto> getAllBooks(){
        return bookService.getAllBooks();
    }

    @PostMapping("/addBook")
    public String addBook(@RequestBody BookDto bookDto){
        bookService.addBook(bookDto);
        return "Книга сохранена";
    }

    @GetMapping("/getBookById/{id}")
    public BookDto getBookById(@PathVariable Long id){
        return bookService.getBookById(id);
    }

    @PutMapping("updateBook")
    public BookDto updateBook(@RequestBody BookDto bookDto){
        return bookService.updateBook(bookDto);
    }

    @GetMapping("getBookByIsbn/{isbn}")
    public BookDto getBookByIsbn (@PathVariable ("isbn") String isbn){
        return bookService.getBookByIsbn(isbn);
    }

    @DeleteMapping("deleteBookById/{id}")
    public String deleteBookById(@PathVariable Long id){
        bookService.deleteBook(id);
        return "Книга с id: " + id + " удалена";
    }
}
