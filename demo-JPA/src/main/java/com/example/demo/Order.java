package com.example.demo;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ordermaster", schema = "world")
public class Order {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String emp;

}