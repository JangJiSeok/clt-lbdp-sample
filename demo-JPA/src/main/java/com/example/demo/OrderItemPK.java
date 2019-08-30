package com.example.demo;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "OrderItem", schema = "world")
public class OrderItemPK implements Serializable {

    private Long id;
    private Long productCode;

}
