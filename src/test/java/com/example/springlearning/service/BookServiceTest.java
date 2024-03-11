package com.example.springlearning.service;

import com.example.springlearning.entity.Author;
import com.example.springlearning.entity.Book;
import com.example.springlearning.dto.BookDto;
import com.example.springlearning.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;
    @Mock
    private AuthorService authorService;
    @InjectMocks
    private BookService bookService;

    @Test
    public void testCreateBook() {
        BookDto dto = BookDto.builder().title("The Secret").price(15).publicationYear(1976).authorId(1L).build();
        Author author = Author.builder().id(1L).name("Rhonda Byrne").build();
        when(authorService.findAuthor(1L)).thenReturn(author);
        BookDto createdBookDto = bookService.create(dto);
        verify(authorService, times(1)).findAuthor(1L);
        verify(bookRepository, times(1)).save(any(Book.class));
        assertEquals(dto, createdBookDto);
    }

    @Test
    void readByIDMethodSuccessTest() {
        Book book = Book.builder().bookId(1L).title("The Secret").price(15).publicationYear(1976).build();
        when(bookRepository.findById(1L)).thenReturn(Optional.ofNullable(book));
        BookDto bookDto = bookService.readByID(1L);
        assertEquals("The Secret", bookDto.getTitle());
    }

    @Test
    public void updateMethodSuccessTest() {
        BookDto dto = BookDto.builder().title("The Secret Updated").price(15).publicationYear(1976).authorId(1L).build();
        Author author = Author.builder().id(1L).name(null).bookdao(null).build();
        Book dao = Book.builder().bookId(1L).authorDao(author).title("The Secret Updated").price(15).publicationYear(1976).build();
        when(bookRepository.save(dao)).thenReturn(dao);
        BookDto updated = bookService.update(dto, 1L);
        assertEquals("The Secret Updated",updated.getTitle());
    }

//    @Test
//    public void testFindBooksByPriceRange() {
//        int minPrice = 10;
//        Author author = Author.builder().id(1L).name(null).bookdao(null).build();
//        List<Book> mockedBooks = Arrays.asList(
//                new Book(1L, "Book1", 15, 1987, author),
//                new Book(2L, "Book2", 17, 1895, author)
//        );
//        when(bookRepository.findByPriceGreaterThan(minPrice)).thenReturn((ArrayList<Book>) mockedBooks);
//        List<BookDto> result = bookService.findBooksByPriceRange(minPrice);
//        assertEquals(mockedBooks, result);
//    }
//
//    @Test
//    void testReadBooks() {
//        List<BookDto> dtos = new ArrayList<>();
//        dtos.add(new BookDto());
//        when(bookService.readAll()).thenReturn(dtos);
//        List<BookDto> result = bookService.readAll();
//        verify(bookService, times(1)).readAll();
//        assertEquals(dtos, result);
//    }
}