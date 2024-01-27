package com.example.springlearning.mapper;

import com.example.springlearning.dao.Book;
import com.example.springlearning.dto.BookDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(source = "authorDao.id", target = "authorId")
    BookDto bookDaoToDto(Book book);

    @Mapping(source = "authorId", target = "authorDao.id")
    Book bookDtoToDao(BookDto bookDto);

    List<BookDto> convertDAOListToDTOList(List<Book> daoList);

    List<Book> convertDTOListToDAOList(List<BookDto> dtoList);
}
