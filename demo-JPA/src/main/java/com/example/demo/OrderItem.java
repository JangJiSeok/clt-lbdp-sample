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
    private Long   id;

    @Id
    private Long productcode;

    private String productname;
    private long   qty;
    private long   price;
}

