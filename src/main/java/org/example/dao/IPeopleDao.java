package org.example.dao;

import org.example.models.Person;

import java.util.Collection;
import java.util.Optional;

public interface IPeopleDao<T extends Person> extends IDao<T> {
    Collection<T> read();

    Optional<T> read(String email);
}