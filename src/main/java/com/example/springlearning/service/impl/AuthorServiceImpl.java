package com.example.springlearning.service.impl;

import com.example.springlearning.dto.AuthorDto;
import com.example.springlearning.entity.Author;
import com.example.springlearning.exception.AlreadyExistException;
import com.example.springlearning.exception.NotFoundException;
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
        Author author = authorRepository.findByAuthorId(id);
        if (author.getName().isEmpty()) {
            throw new NotFoundException("Author Not Found");
        }
        log.info("Finding author by ID: {}", id);
        return author;
    }

    public AuthorDto createAuthor(AuthorDto authorDto) {
        if (authorRepository.findAuthorByName(authorDto.getName()).isPresent()) {
            throw new AlreadyExistException("Author already exist");
        }
        Author authorDao = authorMapper.authorDtoToAuthorDao(authorDto);
        log.info("Created a new author: {}", authorDto.getName());
        return authorDto;
    }

    public List<AuthorDto> getAllAuthors() {
        List<Author> authorDAOList = authorRepository.findAll();
        if (authorDAOList.isEmpty()){
            throw new NotFoundException("Author Not Found");
        }
        return authorMapper.convertDAOListToDTOList(authorDAOList);
    }

    public AuthorDto updateAuthor(Long id, AuthorDto authorDto) {
        authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Author Not Found"));
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
