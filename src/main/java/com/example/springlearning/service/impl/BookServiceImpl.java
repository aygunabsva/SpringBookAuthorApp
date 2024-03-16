package com.example.springlearning.service.impl;

import com.example.springlearning.dto.BookDto;
import com.example.springlearning.entity.Book;
import com.example.springlearning.exception.AlreadyExistException;
import com.example.springlearning.exception.NotFoundException;
import com.example.springlearning.mapper.BookMapper;
import com.example.springlearning.repository.BookRepository;
import com.example.springlearning.service.AuthorService;
import com.example.springlearning.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final BookMapper bookMapper = BookMapper.INSTANCE;

    public BookDto create(BookDto dto) {
        authorService.findAuthor(dto.getAuthorId());
        if (bookRepository.findBookByTitle(dto.getTitle()).isPresent()) {
            throw new AlreadyExistException("Book already exist");
        }
        Book book = bookMapper.bookDtoToDao(dto);
        log.info("Created a new book: {}", dto.getTitle());
        return bookMapper.bookDaoToDto(bookRepository.save(book));
    }

    public List<BookDto> readAll() {
        List<Book> bookDAOList = bookRepository.findAll();
        if (bookDAOList.isEmpty()){
            throw new NotFoundException("Book Not Found");
        }
        return bookMapper.convertDAOListToDTOList(bookDAOList);
    }

    public List<BookDto> findByPriceGreaterThan(int minPrice) {
        List<Book> books = bookRepository.findByPriceGreaterThan(minPrice);
        if (books.isEmpty()) {
            throw new NotFoundException("Book Not Found");
        }
        log.info("Found {} books with a price greater than {}", books.size(), minPrice);
        return bookMapper.convertDAOListToDTOList(books);
    }

    public List<BookDto> findByPriceLowerThan(int maxPrice) {
        List<Book> books = bookRepository.findByPriceLowerThan(maxPrice);
        if (books.isEmpty()) {
            throw new NotFoundException("Book Not Found");
        }
        log.info("Found {} books with a price greater than {}", books.size(), maxPrice);
        return books.stream().map(bookMapper::bookDaoToDto).toList();
    }

    public BookDto readByID(Long id) {
        BookDto bookDto = bookRepository.findById(id)
                .map(bookMapper::bookDaoToDto)
                .orElseThrow(() -> new NotFoundException("Book Not Found"));
        log.info("Retrieved book by ID:{}", id);
        return bookDto;
    }

    public BookDto update(BookDto dto, Long id) {
        Book book = bookMapper.bookDtoToDao(dto);
        book.setBookId(id);
        bookRepository.save(book);
        log.info("Updated book with ID: {}", id);
        return bookMapper.bookDaoToDto(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
        log.info("Deleted book with ID: {}", id);
    }

}
