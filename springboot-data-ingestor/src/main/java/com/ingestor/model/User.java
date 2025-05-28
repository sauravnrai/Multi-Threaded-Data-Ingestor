package com.ingestor.model;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String gender;
    private Integer age;

    public User() {}
    public User(String firstName, String lastName, String phone, String address, String gender, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.age = age;
    }
    // Getters and Setters omitted for brevity
}
