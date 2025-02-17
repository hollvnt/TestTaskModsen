package com.example.booktrackerservice.service.impl;

import com.example.booktrackerservice.config.MapperConfig;
import com.example.booktrackerservice.dto.BookStatusDTO;
import com.example.booktrackerservice.model.BookStatus;
import com.example.booktrackerservice.repository.BookStatusRepository;
import com.example.booktrackerservice.service.BookStatusService;
import lombok.AllArgsConstructor;
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
        bookStatus.setBookStatus("available");
        bookStatusRepository.save(bookStatus);
        return modelMapper.map(bookStatus, BookStatusDTO.class);
    }

    @Override
    public List<BookStatusDTO> getAvailableBook() {
        return bookStatusRepository.findByBookStatus("available")
                .stream()
                .map(bookStatus -> modelMapper.map(bookStatus, BookStatusDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public BookStatusDTO updateBookStatus(Long bookId, String newStatus) {
        BookStatus bookStatus = bookStatusRepository.findByBookId(bookId)
                .orElseThrow(() -> new RuntimeException("Книга с id " + bookId + " не найдена"));

        bookStatus.setBookStatus(newStatus);
        if("taken".equals(newStatus)){
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
            throw new RuntimeException("Книга с id " + bookId + " не найдена");
        }
        bookStatusRepository.deleteById(bookId);
    }
}
