package com.example.demo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.persistence.OrderBy;

public interface BookRepository extends PagingAndSortingRepository<City, Long> {

    @OrderBy("name desc, id asc")
    List<Book> findByName(String name);

}
