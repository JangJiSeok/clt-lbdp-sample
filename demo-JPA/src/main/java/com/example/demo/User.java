package com.example.demo;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user", schema = "world")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String accountNonExpired;
    private String accountNonLocked;
    private String credentialsNonExpired;
    private String enabled;
    private String role;
    private String username;

}
