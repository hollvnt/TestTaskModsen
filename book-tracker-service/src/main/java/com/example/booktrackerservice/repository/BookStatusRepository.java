package com.example.booktrackerservice.repository;

import com.example.booktrackerservice.model.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookStatusRepository extends JpaRepository<BookStatus, Long> {
    Optional<BookStatus> findByBookId(Long bookId);
    List<BookStatus> findByBookStatus(String bookStatus);
}

