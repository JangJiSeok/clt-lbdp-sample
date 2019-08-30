package com.example.demo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "orderitem", schema = "world")
@IdClass(OrderItemPK.class)
public class OrderItem {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private Long   id;

    @Id
    @Column(name = "productcode", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productcode;

    private String productname;
    private long   qty;
    private long   price;
}



@Data
class OrderItemPK implements Serializable {
    private Long id;
    private Long productcode;
}