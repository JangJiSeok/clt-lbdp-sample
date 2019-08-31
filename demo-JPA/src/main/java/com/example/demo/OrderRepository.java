package com.example.demo;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {

}
