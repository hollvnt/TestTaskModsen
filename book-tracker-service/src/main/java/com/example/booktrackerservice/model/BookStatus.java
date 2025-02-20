package com.example.booktrackerservice.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "BookStatus")
public class BookStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    @Enumerated(EnumType.STRING)
    private BookStatusEnum bookStatus;
    private LocalDateTime checkOutTime;
    private LocalDateTime returnTime;
}
