package com.example.demo;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "country", schema = "world")
public class Country {
    @Id
    private String code;
    private String name;

    private String continent;
    private String region;
    private float surfaceArea;
    private int indepYear;
    private int population;
    private float lifeExpectancy;
    private float gNP;
    private String localName;

}
