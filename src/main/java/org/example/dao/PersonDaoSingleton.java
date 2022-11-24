package org.example.dao;

import org.example.models.Person;
import org.example.util.HibernateUtil;

public class PersonDaoSingleton {
    private static volatile PersonDaoSingleton instance;

    private final IPeopleDao<Person> personDao;

    private PersonDaoSingleton() {
        personDao = new PeopleDao(HibernateUtil.getEntityManager());
    }

    public static IPeopleDao<Person> getInstance() {
        if (instance == null) {
            synchronized (PersonDaoSingleton.class) {
                if (instance == null) {
                    instance = new PersonDaoSingleton();
                }
            }
        }
        return instance.personDao;
    }
}