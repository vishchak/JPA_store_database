package com.gmail.vishchak.denis.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Orders")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private Long number;

//    @Temporal(TemporalType.DATE)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "client_id")
    Client client;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "orders_goods",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "goods_id"))
    private List<Product> products = new ArrayList<>();

    public Order(Long number, LocalDate date) {
        this.number = number;
        this.date = date;
    }

    public void addProducts(Product product) {
            products.add(product);
            product.getOrders().add(this);
    }
}
