package com.example.springlearning.repository;

import com.example.springlearning.dao.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    ArrayList<Book> findAll();

    @Query("SELECT b FROM Book b WHERE b.price > :price")
    ArrayList<Book> findByPriceGreaterThan(@Param("price") int price);

    @Query(value = "SELECT * FROM BookDao WHERE price < :maxPrice", nativeQuery = true)
    List<Book> findBooksByPriceLowerThan(@Param("maxPrice") int maxPrice);

}