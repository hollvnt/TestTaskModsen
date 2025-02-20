package com.example.bookstorageservice.Config;


import com.example.bookstorageservice.DTO.BookStatusDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "book-tracker-service", url = "http://localhost:8082/api/book-status")
public interface BookStatusFeignClient {

    @PostMapping("/addBookStatus")
    BookStatusDTO addBookStatus(@RequestBody BookStatusDTO bookStatusDTO);

    @DeleteMapping("/deleteBookStatus/{bookId}")
    void deleteBookStatus(@PathVariable Long bookId);
}
