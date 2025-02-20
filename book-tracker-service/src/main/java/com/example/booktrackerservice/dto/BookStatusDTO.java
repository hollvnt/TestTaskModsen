package com.example.booktrackerservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class BookStatusDTO {
    private Long bookId;
    private String bookStatus;
    private LocalDateTime checkOutTime;
    private LocalDateTime returnTime;
}
