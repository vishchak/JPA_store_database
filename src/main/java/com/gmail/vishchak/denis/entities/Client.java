package com.gmail.vishchak.denis.entities;

import lombok.*;


import javax.persistence.*;
import java.util.HashSet;

import java.util.Set;

@Entity
@Table
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private String surname;

    @Column(unique = true)
    private Long phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @ToString.Exclude
    Address address;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Order> orders = new HashSet<>();

    public Client(String name, String surname, Long phoneNumber, Address address) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public void addOrder(Order order) {
        orders.add(order);
        order.setClient(this);
    }
}

