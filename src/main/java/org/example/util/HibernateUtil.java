package org.example.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class HibernateUtil {
    private static final EntityManagerFactory entityManagerFactory;

    static {
        entityManagerFactory = javax.persistence.Persistence.createEntityManagerFactory("org.example");
    }

    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public static void shutdown() {
        entityManagerFactory.close();
    }
}