package com.example.booktrackerservice.Repository;

import com.example.booktrackerservice.Model.BookStatus;
import com.example.booktrackerservice.Model.BookStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookStatusRepository extends JpaRepository<BookStatus, Long> {
    Optional<BookStatus> findByBookId(Long bookId);
    List<BookStatus> findByBookStatus(BookStatusEnum bookStatus);
}

