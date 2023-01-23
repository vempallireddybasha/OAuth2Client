package com.example.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id

    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private  String name;

    private String password;

    private  long salary;
    private String gender;
    private String authorities;
}
