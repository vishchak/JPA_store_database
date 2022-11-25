package com.gmail.vishchak.denis.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String city;

    private String street;

    private String unit;

    @OneToOne(mappedBy = "address")
    Client client;
}
