package com.gmail.vishchak.denis.entities;

import lombok.*;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String productName;

    @ManyToMany(mappedBy = "products")
    @ToString.Exclude
    private List<Order> orders = new ArrayList<>();

    public Product(String productName) {
        this.productName = productName;
    }

    public void addOrder(Order order) {
        orders.add(order);
        order.getProducts().add(this);
    }
}
