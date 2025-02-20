package com.example.bookstorageservice.DTO;

import com.example.bookstorageservice.Model.BookStatusEnum;
import lombok.*;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class BookStatusDTO {
    private Long bookId;
    private BookStatusEnum bookStatus;
    private LocalDateTime checkOutTime;
    private LocalDateTime returnTime;
}
