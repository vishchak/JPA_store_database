package com.gmail.vishchak.denis.entities;

import lombok.*;

import javax.persistence.*;


@Entity
@Table
@Getter
@Setter
@ToString
@RequiredArgsConstructor
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

    public Address(String city, String street, String unit) {
        this.city = city;
        this.street = street;
        this.unit = unit;
    }
}
