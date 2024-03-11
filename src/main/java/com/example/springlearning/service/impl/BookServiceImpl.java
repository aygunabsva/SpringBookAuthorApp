package com.example.springlearning.service.impl;

import com.example.springlearning.dto.BookDto;
import com.example.springlearning.entity.Book;
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
        Book book = bookMapper.bookDtoToDao(dto);
        bookRepository.save(book);
        log.info("Created a new book: {}", dto.getTitle());
        return dto;
    }

    public List<BookDto> readAll() {
        List<Book> bookDAOList = bookRepository.findAll();
        return bookMapper.convertDAOListToDTOList(bookDAOList);
    }

    public List<BookDto> findBooksByPriceRange(int minPrice) {
        List<Book> books = bookRepository.findByPriceGreaterThan(minPrice);
        log.info("Found {} books with a price greater than {}", books.size(), minPrice);
        return bookMapper.convertDAOListToDTOList(books);
    }

    public BookDto readByID(Long id) {
        Book bookDao = bookRepository.findById(id).get();
        log.info("Retrieved book by ID: {}", id);
        return bookMapper.bookDaoToDto(bookDao);
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
