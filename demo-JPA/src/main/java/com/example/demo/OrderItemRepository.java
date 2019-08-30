package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OrderItemRepository extends PagingAndSortingRepository<OrderItem, OrderItemPK> {

    List<OrderItem> findByProductName(String productName);

}
