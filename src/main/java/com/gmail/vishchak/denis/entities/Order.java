package com.gmail.vishchak.denis.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private Long number;

    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "client_id")
    Client client;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "orders_goods",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "goods_id"))
    private List<Product> products = new ArrayList<>();

    public void addGoods(Product product) {
        products.add(product);
        product.getOrders().add(this);
    }
}
