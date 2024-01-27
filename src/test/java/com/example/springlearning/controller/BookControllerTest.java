package com.example.springlearning.controller;

import com.example.springlearning.dto.BookDto;
import com.example.springlearning.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {
    @Mock
    BookService bookService;
    @InjectMocks
    BookController bookController;

    @Test
    void readByIDMethodSuccessTest() {
        BookDto dto = BookDto.builder().title("The Secret").build();
        when(bookService.readByID(1L)).thenReturn(dto);
        BookDto bookDto = bookController.readByID(1L);
        assertEquals("The Secret", bookDto.getTitle());
    }

    @Test
    public void createMethodSuccessTest() {
        BookDto dto = BookDto.builder().title("The Secret").price(15).publicationYear(1976).authorId(1L).build();
        when(bookService.create(dto)).thenReturn(dto);
        BookDto createdBook = bookController.create(dto);
        verify(bookService, times(1)).create(dto);
        assertEquals(dto, createdBook);
    }

    @Test
    public void updateMethodSuccessTest() {
        BookDto dto = new BookDto();
        dto.setTitle("The Secret");
        when(bookService.update(dto, 1L)).thenReturn(dto);
        BookDto updatedBook = bookController.update(dto, 1L);
        verify(bookService, times(1)).update(dto, 1L);
        assertEquals(dto, updatedBook);
    }

    @Test
    void readAllMethodSuccessTest() {
        List<BookDto> dtos = new ArrayList<>();
        dtos.add(new BookDto());
        when(bookService.readAll()).thenReturn(dtos);
        List<BookDto> result = bookController.readAll();
        verify(bookService, times(1)).readAll();
        assertEquals(dtos, result);
    }
}