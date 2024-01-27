package com.example.springlearning.mapper;

import com.example.springlearning.dao.Author;
import com.example.springlearning.dto.AuthorDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDto authorDaoToAuthorDto(Author authorDao);

    Author authorDtoToAuthorDao(AuthorDto authorDto);

    List<AuthorDto> convertDAOListToDTOList(List<Author> daoList);

    List<Author> convertDTOListToDAOList(List<AuthorDto> dtoList);
}
