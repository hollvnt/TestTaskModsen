package com.example.demo1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class BookStatusDTO {
    private Long bookId;
    private String bookStatus;
    private LocalDateTime checkOutTime;
    private LocalDateTime returnTime;
}
