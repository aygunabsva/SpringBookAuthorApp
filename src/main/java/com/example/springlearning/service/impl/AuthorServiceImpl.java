package com.example.springlearning.service.impl;

import com.example.springlearning.dto.AuthorDto;
import com.example.springlearning.entity.Author;
import com.example.springlearning.mapper.AuthorMapper;
import com.example.springlearning.repository.AuthorRepository;
import com.example.springlearning.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper = AuthorMapper.INSTANCE;

    public Author findAuthor(Long id) {
        log.info("Finding author by ID: {}", id);
        return authorRepository.findByAuthorId(id);
    }

    public AuthorDto createAuthor(AuthorDto authorDto) {
        Author authorDao = authorMapper.authorDtoToAuthorDao(authorDto);
        Author savedAuthor = authorRepository.save(authorDao);
        log.info("Created a new author: {}", savedAuthor.getName());
        return authorMapper.authorDaoToAuthorDto(savedAuthor);
    }

    public List<AuthorDto> getAllAuthors() {
        List<Author> authorDAOList = authorRepository.findAll();
        return authorMapper.convertDAOListToDTOList(authorDAOList);
    }

    public AuthorDto updateAuthor(Long id, AuthorDto authorDto) {
        authorRepository.findById(id).get();
        Author author = authorMapper.authorDtoToAuthorDao(authorDto);
        author.setId(id);
        authorRepository.save(author);
        log.info("Updated author with ID: {}", id);
        return authorMapper.authorDaoToAuthorDto(author);
    }
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
        log.info("Deleted author with ID: {}", id);
    }

}
