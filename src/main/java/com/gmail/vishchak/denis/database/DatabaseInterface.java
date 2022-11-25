package com.gmail.vishchak.denis.database;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.Scanner;

public interface DatabaseInterface {
    public Long add(EntityManager em, Scanner sc);
    public void findByDate(EntityManager em, Date from, Date to);

    public void findByNumber(EntityManager em, Long number);
}
