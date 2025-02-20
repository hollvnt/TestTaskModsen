package com.example.bookstorageservice.DTO;

import com.example.bookstorageservice.Model.BookStatusEnum;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private Long id;
    private String isbn;
    private String title;
    private String genre;
    private String description;
    private String author;
}