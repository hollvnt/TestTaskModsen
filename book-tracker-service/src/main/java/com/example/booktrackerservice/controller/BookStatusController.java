package com.example.booktrackerservice.controller;

import com.example.booktrackerservice.dto.BookStatusDTO;
import com.example.booktrackerservice.service.BookStatusService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("api/book-status")
public class BookStatusController {
    private final BookStatusService bookStatusService;

    @PostMapping("/addBookStatus")
    public BookStatusDTO addBookStatus (BookStatusDTO bookStatusDTO){
        try{
            return bookStatusService.addBookStatus(bookStatusDTO);
        } catch (Exception e){
            throw new RuntimeException("Ошибка при добавлении статуса книги: " + e.getMessage());
        }
    }

    @GetMapping()
    public List<BookStatusDTO> getAvailableBook(){
        try{
            return bookStatusService.getAvailableBook();
        } catch (Exception e){
            throw new RuntimeException("Ошибка при получении статуса книги: " + e.getMessage());
        }
    }

    @PutMapping("/updateBookStatus/{bookId}")
    public BookStatusDTO updateBookStatus(@PathVariable Long bookId, @RequestBody Map<String, String> requestBody ){
        try{
            String newStatus = requestBody.get("bookStatus");
            return bookStatusService.updateBookStatus(bookId, newStatus);
        } catch (Exception e){
            throw new RuntimeException("Ошибка при обновлении статуса книги с id: " + bookId + e.getMessage());
        }
    }

    @DeleteMapping("/deleteBookStatus/{bookId}")
    public String deleteBookStatus(@PathVariable Long bookId){
        try {
            bookStatusService.deleteBookStatus(bookId);
            return "Запись о книге c id " + bookId + " успешно удалена";
        } catch (Exception e){
            throw new RuntimeException("Ошибка при удалении статуса книги с id: " + bookId + e.getMessage());
        }
    }
}
