package com.example.springlearning.controller;

import com.example.springlearning.dto.BookDto;
import com.example.springlearning.entity.Book;
import com.example.springlearning.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "books")
@RequiredArgsConstructor
public class BookController {
    private final BookService service;

//    @PostMapping("create")
//    @ResponseStatus(HttpStatus.CREATED)
//    public BookDto create(@RequestBody BookDto dto) {
//        service.create(dto);
//        return dto;
//    }
//
    @GetMapping("readAll")
    public List<BookDto> readAll() {
        return service.readAll();
    }
//
    @GetMapping("readById")
    public BookDto readByID(@RequestParam Long id) {
        return service.readByID(id);
    }
//
//    @GetMapping("readByPrice/Min")
//    public List<BookDto> readByMinPrice(
//            @RequestParam("minPrice") int minPrice) {
//        return service.findByPriceGreaterThan(minPrice);
//    }
//
//    @GetMapping("readByPrice/Max")
//    public List<BookDto> readByMaxPrice(
//            @RequestParam("maxPrice") int maxPrice) {
//        return service.findByPriceLowerThan(maxPrice);
//    }
//
//    @PutMapping("update")
//    public BookDto update(@RequestBody BookDto dto, @RequestParam Long id) {
//        return service.update(dto, id);
//    }
//
//    @DeleteMapping("delete")
//    public ResponseEntity<Book> delete(@RequestParam Long id) {
//        service.delete(id);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//    }

}