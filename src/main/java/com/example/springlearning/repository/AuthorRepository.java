package com.example.springlearning.repository;

import com.example.springlearning.entity.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    ArrayList<Author> findAll();
    Optional<Author> findAuthorByName(String name);
    @Query(value = "SELECT * FROM Author WHERE id = :id", nativeQuery = true)
    Author findByAuthorId(@Param("id") long id);
}
