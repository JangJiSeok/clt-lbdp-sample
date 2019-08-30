package com.example.demo;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Order", schema = "world")
public class OrderMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String emp;

}