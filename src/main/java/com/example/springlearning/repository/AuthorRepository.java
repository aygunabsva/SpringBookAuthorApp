package com.example.springlearning.repository;

import com.example.springlearning.entity.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    ArrayList<Author> findAll();
    @Query(value = "SELECT * FROM Author WHERE id = :id", nativeQuery = true)
    Author findByAuthorId(@Param("id") long id);
}
