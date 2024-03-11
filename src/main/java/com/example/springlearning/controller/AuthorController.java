package com.example.springlearning.controller;

import com.example.springlearning.entity.Author;
import com.example.springlearning.dto.AuthorDto;
import com.example.springlearning.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping("create")
    public AuthorDto createAuthor(@RequestBody AuthorDto authorDto) {
        authorService.createAuthor(authorDto);
        return authorDto;
    }
    @GetMapping("readByID")
    public Author getAuthorById(@RequestParam Long authorId) {
        return authorService.findAuthor(authorId);
    }

    @GetMapping("readAllAuthors")
    public List<AuthorDto> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @PutMapping("update")
    public AuthorDto updateAuthor(@RequestParam Long id, @RequestBody AuthorDto authorDto) {
        return authorService.updateAuthor(id, authorDto);
    }

    @DeleteMapping("delete")
    public String deleteAuthor(@RequestParam Long id) {
        authorService.deleteAuthor(id);
        return HttpStatus.OK.name();
    }
}