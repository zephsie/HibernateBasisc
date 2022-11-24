package org.example.dao;

import java.util.Optional;

public interface IDao<T> {
    T create(T t);

    T update(T t);

    Optional<T> read(Long id);

    void delete(T t);
}