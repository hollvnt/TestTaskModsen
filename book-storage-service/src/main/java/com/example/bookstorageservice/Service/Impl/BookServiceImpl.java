package com.example.bookstorageservice.Service.Impl;

import com.example.bookstorageservice.Constants.ErrorMessages;
import com.example.bookstorageservice.Exception.BookNotFoundException;
import com.example.bookstorageservice.Exception.BookOperationException;
import com.example.bookstorageservice.Model.BookStatusEnum;
import com.example.bookstorageservice.Service.BookService;
import com.example.bookstorageservice.Config.BookStatusFeignClient;
import com.example.bookstorageservice.DTO.*;
import com.example.bookstorageservice.Model.Book;
import com.example.bookstorageservice.Repository.BookRepository;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final BookStatusFeignClient bookStatusFeignClient;

    @Override
    public BookDto addBook(BookDto bookDto) {
        try{
            Book book = modelMapper.map(bookDto, Book.class);
            Book savedBook = bookRepository.save(book);

            BookStatusDTO bookStatusDTO = new BookStatusDTO();
            bookStatusDTO.setBookId(savedBook.getId());
            bookStatusDTO.setBookStatus(BookStatusEnum.AVAILABLE);

            bookStatusFeignClient.addBookStatus(bookStatusDTO);

            return modelMapper.map(savedBook, BookDto.class);
        } catch (Exception e){
            throw new BookOperationException(ErrorMessages.ADD_BOOK_ERROR + e.getMessage());
        }
    }

    @Override
    public List<BookDto> getAllBooks() {
        try {
            List<Book> books = bookRepository.findAll();
            return books.stream()
                    .map(book -> modelMapper.map(book, BookDto.class))
                    .collect(Collectors.toList());
        }catch (Exception e){
            throw new BookNotFoundException(ErrorMessages.GET_BOOK_ERROR + e.getMessage());
        }
    }

    @Override
    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(ErrorMessages.BOOK_NOT_FOUND_ID));
        return modelMapper.map(book, BookDto.class);
    }

    @Override
    public BookDto getBookByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(ErrorMessages.BOOK_NOT_FOUND_ISBN));
        return modelMapper.map(book, BookDto.class);
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {
        try{
            Book book = modelMapper.map(bookDto, Book.class);
            Book updateBook = bookRepository.save(book);
            return modelMapper.map(updateBook, BookDto.class);
        }catch (Exception e){
            throw new BookOperationException(ErrorMessages.UPDATE_BOOK_ERROR + e.getMessage());
        }
    }

    @Override
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException(ErrorMessages.BOOK_NOT_FOUND_ID);
        }

        try{
            if (bookRepository.existsById(id)) {
                bookRepository.deleteById(id);
                bookStatusFeignClient.deleteBookStatus(id);
            }
        }catch (Exception e){
            throw new BookOperationException(ErrorMessages.DELETE_BOOK_ERROR + e.getMessage());
        }
    }

}
