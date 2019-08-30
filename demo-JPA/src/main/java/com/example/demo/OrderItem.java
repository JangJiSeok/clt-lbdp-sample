package com.example.demo;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "OrderItem", schema = "world")
@IdClass(OrderItemPK.class)
public class OrderItem {

//    @EmbeddedId
//    private OrderItemPK orderItemPK;

    @Id
    private long   id;
    @Id
    private long productCode;

    private String productName;
    private long   qty;
    private long   price;

}