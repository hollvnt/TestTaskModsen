package com.example.demo1.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class BookDto {
    private Long id;
    private String isbn;
    private String title;
    private String genre;
    private String description;
    private String author;

}