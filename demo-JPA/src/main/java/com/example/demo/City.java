package com.example.demo;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "city", schema = "world")
public class City implements Serializable {
    public City() {}
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    public Long id;

    @Column(name = "name", updatable = false, nullable = false)
    public String name;

    public String countrycode;
    
    
    @Column(name = "district", updatable = false, nullable = true)
    public String district;
    
    @Column(name = "population", updatable = false, nullable = true)
    public int population;
    
    
    
}