package com.example.demo1.Service.Impl;
import com.example.demo1.Service.BookService;
import com.example.demo1.config.BookStatusFeignClient;
import com.example.demo1.dto.BookDto;
import com.example.demo1.dto.BookStatusDTO;
import com.example.demo1.model.Book;
import com.example.demo1.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
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
        try {
            Book book = modelMapper.map(bookDto, Book.class);
            Book savedBook = bookRepository.save(book);

            BookStatusDTO bookStatusDTO = new BookStatusDTO();
            bookStatusDTO.setBookId(savedBook.getId());
            bookStatusDTO.setBookStatus("available");

            bookStatusFeignClient.addBookStatus(bookStatusDTO);

            return modelMapper.map(savedBook, BookDto.class);
        } catch (Exception e) {
            log.error("Ошибка при добавлении книги", e);
            throw new RuntimeException("Не удалось добавить книгу: " + e.getMessage());
        }
    }


    @Override
    public List<BookDto> getAllBooks() {
       try {
           List<Book> book = bookRepository.findAll();
           return book.stream()
                   .map(books -> modelMapper.map(books, BookDto.class))
                   .collect(Collectors.toList());

       } catch (Exception e){
           log.error("Ошибка при получении списка книг", e);
           throw new RuntimeException("Не удалось получить книги");
        }
    }

    @Override
    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("книги с id " + id + " не найдено"));
        return modelMapper.map(book, BookDto.class);
    }

    @Override
    public BookDto getBookByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new RuntimeException("книги с isbn " + isbn + " не найдено"));
        return modelMapper.map(book, BookDto.class);
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {
        try{
            Book book = modelMapper.map(bookDto, Book.class);
            Book updateBook = bookRepository.save(book);
            return modelMapper.map(updateBook, BookDto.class);
        } catch (Exception e){
            log.error("Ошибка обновления книги");
            throw new RuntimeException("Не удалось обновить книгу");
        }
    }

    @Override
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Книга с id " + id + " не найдена");
        }

        try{
           if(bookRepository.existsById(id)){
               bookRepository.deleteById(id);
               bookStatusFeignClient.deleteBookStatus(id);
           }
        } catch (Exception e){
            log.error("Ошибка при удалении книги с id " + id, e);
        }
    }

}
