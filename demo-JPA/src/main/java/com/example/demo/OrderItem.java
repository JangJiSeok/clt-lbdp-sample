package com.example.demo;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@Table(name = "orderitem", schema = "world")
@IdClass(OrderItemPK.class)
public class OrderItem extends AuditModel{

    @Id
    @MapsId("id")
    @JoinColumn(name = "id")
    private Long   id;

    @Id
    @Column(name = "productcode", insertable = false, updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productcode;

    @NotNull(message = "productname is not allowed")
    private String productname;
    @Min(value = 0L,message = "The qty must be bigger then 0")
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