package com.example.booktrackerservice.Service;

import com.example.booktrackerservice.DTO.BookStatusDTO;

import java.util.List;

public interface BookStatusService {
    BookStatusDTO addBookStatus(BookStatusDTO bookStatusDTO);
    List<BookStatusDTO> getAvailableBook();
    BookStatusDTO updateBookStatus(Long bookId, String newStatus);
    void deleteBookStatus(Long bookId);
}
