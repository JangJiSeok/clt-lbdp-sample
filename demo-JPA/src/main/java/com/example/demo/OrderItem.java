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
    @MapsId("id")
    @ManyToOne
    @JoinColumn(name = "id")
    private Long   id;

    @Id
    @Column(name = "productcode", insertable = false, updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productcode;

    private String productname;
    private long   qty;
    private long   price;
}



@Data
@Embeddable
class OrderItemPK implements Serializable {

    private Long id;

    @Id
    @Column(name = "productcode", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productcode;
}