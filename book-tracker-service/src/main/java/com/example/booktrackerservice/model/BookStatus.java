package com.example.booktrackerservice.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "BookStatus")
public class BookStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private String bookStatus;
    private LocalDateTime checkOutTime;
    private LocalDateTime returnTime;
}
