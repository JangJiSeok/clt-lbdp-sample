package com.example.demo;

import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;

@Data
//@Table(name = "orderitem", schema = "world")
public class OrderItemPK implements Serializable {
    private Long id;
    private Long productcode;
}