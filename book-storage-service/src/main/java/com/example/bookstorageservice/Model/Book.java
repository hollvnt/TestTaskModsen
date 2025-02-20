package com.example.bookstorageservice.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String isbn;
    private String title;
    private String genre;
    private String description;
    private String author;
}