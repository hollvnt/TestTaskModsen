package com.example.bookstorageservice.Controller;

import com.example.bookstorageservice.Service.Impl.BookServiceImpl;
import com.example.bookstorageservice.DTO.BookDto;
import org.junit.jupiter.api.BeforeEach;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class BookControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @Mock
    private BookServiceImpl bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    void getAllBooks() throws Exception {
        List<BookDto> books = Arrays.asList(
                new BookDto(1L, "123456", "Book Title", "Fiction", "Some description", "Author Name"),
                new BookDto(2L, "789012", "Another Book", "Non-Fiction", "Another description", "Another Author")
        );
        when(bookService.getAllBooks()).thenReturn(books);

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk());
    }

    @Test
    void getBookById() throws Exception {
        Long bookId = 1L;
        BookDto bookDto = new BookDto(bookId, "123456", "Book Title", "Fiction", "Description", "Author");

        when(bookService.getBookById(bookId)).thenReturn(bookDto);

        mockMvc.perform(get("/api/books/getBookById/{id}", bookId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(bookId))
                .andExpect(jsonPath("$.title").value("Book Title"));
    }

    @Test
    void getBookByIsbn() throws Exception {
        String isbn = "123456";
        BookDto bookDto = new BookDto(1L, isbn, "Book Title", "Fiction", "Description", "Author");

        when(bookService.getBookByIsbn(isbn)).thenReturn(bookDto);

        mockMvc.perform(get("/api/books/getBookByIsbn/{isbn}", isbn))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isbn").value(isbn))
                .andExpect(jsonPath("$.title").value("Book Title"));
    }

    @Test
    void deleteBookById() throws Exception {
        Long bookId = 1L;

        doNothing().when(bookService).deleteBook(bookId);

        mockMvc.perform(delete("/api/books/deleteBookById/{id}", bookId))
                .andExpect(status().isOk());
    }


}