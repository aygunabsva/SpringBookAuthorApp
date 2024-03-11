package com.example.springlearning.service;

import com.example.springlearning.dto.AuthorDto;
import com.example.springlearning.entity.Author;

import java.util.List;


public interface AuthorService {

    Author findAuthor(Long id);

    AuthorDto createAuthor(AuthorDto authorDto);

    List<AuthorDto> getAllAuthors();

    AuthorDto updateAuthor(Long id, AuthorDto authorDto);

    void deleteAuthor(Long id);

}
