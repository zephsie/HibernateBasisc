package org.example.dao;

import java.util.Collection;
import java.util.Optional;

public interface IDao<T> {
    T create(T t);

    T update(T t);

    Optional<T> read(Long id);

    Collection<T> read();

    void delete(T t);
}