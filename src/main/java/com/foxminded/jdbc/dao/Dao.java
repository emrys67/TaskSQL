package com.foxminded.jdbc.dao;

public interface Dao<T> {
    T findById(Long id);

    boolean deleteById(Long id);

    boolean insert(T t);
}
