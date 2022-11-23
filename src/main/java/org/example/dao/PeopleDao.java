package org.example.dao;

import org.example.models.Person;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.Optional;

public class PeopleDao implements IDao<Person> {
    private final EntityManager entityManager;

    public PeopleDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Person create(Person person) {
        entityManager.getTransaction().begin();
        entityManager.persist(person);
        entityManager.getTransaction().commit();
        return person;
    }

    @Override
    public Person update(Person person) {
        entityManager.getTransaction().begin();
        entityManager.merge(person);
        entityManager.getTransaction().commit();
        return person;
    }

    @Override
    public Optional<Person> read(Long id) {
        return Optional.ofNullable(entityManager.find(Person.class, id));
    }

    @Override
    public Collection<Person> read() {
        return entityManager.createQuery("SELECT DISTINCT p FROM Person p JOIN FETCH p.billingDetails", Person.class).getResultList();
    }

    @Override
    public void delete(Person person) {
        entityManager.getTransaction().begin();
        entityManager.remove(person);
        entityManager.getTransaction().commit();
    }
}