package com.gmail.vishchak.denis;

import com.gmail.vishchak.denis.database.DatabaseClientImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Main {

    static EntityManagerFactory emf;

    static EntityManager em;

    static Scanner sc;

    static DatabaseClientImpl db;

    public static void main(String[] args) {
        db = new DatabaseClientImpl();
        emf = Persistence.createEntityManagerFactory("StoreJPA");
        em = emf.createEntityManager();
        sc = new Scanner(System.in);


        Long orderNumber = db.add(em, sc);
        db.findByNumber(em, orderNumber);
    }
}
