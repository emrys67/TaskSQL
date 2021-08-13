package com.foxminded.jdbc.dao;

import com.foxminded.jdbc.entity.Student;

public interface Dao<T> {
    T findById(Long id);

    boolean deleteById(Long id);

    boolean insert(T t);

}
