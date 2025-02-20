package com.example.booktrackerservice.Controller;

import com.example.booktrackerservice.Constants.ErrorMessage;
import com.example.booktrackerservice.DTO.BookStatusDTO;
import com.example.booktrackerservice.Service.BookStatusService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
            throw new RuntimeException(ErrorMessage.ADD_BOOK_ERROR + e.getMessage());
        }
    }

    @GetMapping()
    public List<BookStatusDTO> getAvailableBook(){
        try{
            return bookStatusService.getAvailableBook();
        } catch (Exception e){
            throw new RuntimeException(ErrorMessage.GET_BOOK_ERROR + e.getMessage());
        }
    }

    @PutMapping("/updateBookStatus/{bookId}")
    public BookStatusDTO updateBookStatus(@PathVariable Long bookId, @RequestBody Map<String, String> requestBody ){
        try{
            String newStatus = requestBody.get("bookStatus");
            return bookStatusService.updateBookStatus(bookId, newStatus);
        } catch (Exception e){
            throw new RuntimeException(ErrorMessage.UPDATE_BOOK_ERROR + bookId + e.getMessage());
        }
    }

    @DeleteMapping("/deleteBookStatus/{bookId}")
    public String deleteBookStatus(@PathVariable Long bookId){
        try {
            bookStatusService.deleteBookStatus(bookId);
            return ErrorMessage.BOOK_DELETED;
        } catch (Exception e){
            throw new RuntimeException(ErrorMessage.DELETE_BOOK_ERROR + bookId + e.getMessage());
        }
    }
}
