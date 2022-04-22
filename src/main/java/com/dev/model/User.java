package com.dev.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class User {
    @Id
    private Long id;

    private String name;

    private String username;

    private String email;

    @ManyToOne
    private Address address;

    private String phone;

    private String website;

    @ManyToOne
    private Company company;
}
