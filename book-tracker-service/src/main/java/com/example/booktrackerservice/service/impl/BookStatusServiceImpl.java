package com.example.booktrackerservice.Service.Impl;

import com.example.booktrackerservice.Constants.ErrorMessage;
import com.example.booktrackerservice.DTO.BookStatusDTO;
import com.example.booktrackerservice.Model.BookStatus;
import com.example.booktrackerservice.Model.BookStatusEnum;
import com.example.booktrackerservice.Repository.BookStatusRepository;
import com.example.booktrackerservice.Service.BookStatusService;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookStatusServiceImpl implements BookStatusService {
    private final ModelMapper modelMapper;
    private final BookStatusRepository bookStatusRepository;

    @Override
    public BookStatusDTO addBookStatus(BookStatusDTO bookStatusDTO) {
        BookStatus bookStatus = modelMapper.map(bookStatusDTO, BookStatus.class);
        bookStatus.setBookStatus(BookStatusEnum.AVAILABLE);
        bookStatusRepository.save(bookStatus);
        return modelMapper.map(bookStatus, BookStatusDTO.class);
    }

    @Override
    public List<BookStatusDTO> getAvailableBook() {
        return bookStatusRepository.findByBookStatus(BookStatusEnum.AVAILABLE)
                .stream()
                .map(bookStatus -> modelMapper.map(bookStatus, BookStatusDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public BookStatusDTO updateBookStatus(Long bookId, String newStatus) {
        BookStatus bookStatus = bookStatusRepository.findByBookId(bookId)
                .orElseThrow(() -> new RuntimeException("Книга с id " + bookId + " не найдена"));

        bookStatus.setBookStatus(BookStatusEnum.valueOf(newStatus.toUpperCase()));
        if(BookStatusEnum.TAKEN.equals(bookStatus.getBookStatus())){
            bookStatus.setCheckOutTime(LocalDateTime.now());
            bookStatus.setReturnTime(LocalDateTime.now().plusWeeks(2));
        } else {
            bookStatus.setCheckOutTime(null);
            bookStatus.setReturnTime(null);
        }

        bookStatusRepository.save(bookStatus);
        return modelMapper.map(bookStatus, BookStatusDTO.class);
    }

    @Override
    public void deleteBookStatus(Long bookId) {
        if(!bookStatusRepository.existsById(bookId)){
            throw new RuntimeException(String.format(ErrorMessage.BOOK_NOT_FOUND));
        }
        bookStatusRepository.deleteById(bookId);
    }
}
