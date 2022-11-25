package com.gmail.vishchak.denis.database;

import com.gmail.vishchak.denis.entities.Address;
import com.gmail.vishchak.denis.entities.Client;
import com.gmail.vishchak.denis.entities.Order;
import com.gmail.vishchak.denis.entities.Product;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class DatabaseClientImpl implements DatabaseInterface {
    public static Client addClient(EntityManager em, Scanner sc) {
        System.out.println("Input your name:");
        String name = sc.nextLine();
        System.out.println("Input your surname:");
        String surname = sc.nextLine();
        System.out.println("Input your mobile number:");
        Long mobileNumber = sc.nextLong();
        Address address = addAddress(em, sc);
        em.getTransaction().begin();
        try {
            Client client = new Client(name, surname, mobileNumber, address);
            em.persist(client);
            em.getTransaction().commit();
            return client;
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
        throw new RuntimeException();
    }

    private static Address addAddress(EntityManager em, Scanner sc) {
        sc.nextLine();
        System.out.println("Input your city:");
        String city = sc.nextLine();
        System.out.println("Input your street:");
        String street = sc.nextLine();
        System.out.println("Input your unit:");
        String unit = sc.nextLine();

        em.getTransaction().begin();
        try {
            Address address = new Address(city, street, unit);
            em.persist(address);
            em.getTransaction().commit();
            return address;
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
        throw new RuntimeException();
    }

    public static void addProduct(EntityManager em) {
        em.getTransaction().begin();
        try {
            Product[] products = new Product[]{new Product("Book"), new Product("Lamp"), new Product("Laptop"), new Product("Onion")};
            for (Product p :
                    products) {
                em.persist(p);
            }
            em.getTransaction().commit();
            return;
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
        throw new RuntimeException();
    }


    public static Order addOrder(EntityManager em, Scanner sc, Client client) {
        Order order = new Order(new Random().nextLong(), LocalDate.now());
        order.setClient(client);
        TypedQuery<Product> typedQuery = em.createQuery("SELECT c FROM Product c", Product.class);
        List<Product> productList = typedQuery.getResultList();
        em.getTransaction().begin();
        try {
            for (Product p :
                    productList) {
                System.out.println("Do u wanna add " + p + " to the shopping bag?" +
                        "\n 1: YES" +
                        "\n 2: NO" +
                        "\n -->");
                int decision = sc.nextInt();
                if (decision == 1) {
                    order.addProducts(p);
                }
            }
            if (order.getProducts().size() != 0) {
                em.persist(order);
                em.getTransaction().commit();
                return order;
            }
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
        throw new RuntimeException();
    }

    @Override
    public void add(EntityManager em, Scanner sc) {
        Client client = addClient(em, sc);
        addProduct(em);
        client.addOrder(addOrder(em, sc, client));
    }

    @Override
    public void findByDate(EntityManager em, Date date) {

    }

    @Override
    public void findByNumber(EntityManager em, Long number) {

    }
}