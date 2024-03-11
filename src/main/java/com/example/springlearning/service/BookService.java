package com.example.springlearning.service;

import com.example.springlearning.dto.BookDto;

import java.util.List;


public interface BookService {

    BookDto create(BookDto dto);

    List<BookDto> readAll();

    List<BookDto> findBooksByPriceRange(int minPrice);

    BookDto readByID(Long id);

    BookDto update(BookDto dto, Long id);

    void delete(Long id);
}