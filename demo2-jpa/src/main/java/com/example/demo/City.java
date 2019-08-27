package com.example.demo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

    @Column(name = "countrycode", updatable = false, nullable = true)
    public String countrycode;
    
    
    @Column(name = "district", updatable = false, nullable = true)
    public String district;
    
    @Column(name = "population", updatable = false, nullable = true)
    public int population;
    
    
    
}