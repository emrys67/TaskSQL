package com.foxminded.jdbc.dao;

import com.foxminded.jdbc.entity.Student;

import java.util.List;

public interface StudentDao<T> extends Dao<T> {
    boolean setGroup(Long studentId, Long groupId);

    List<Student> findStudentsRelatedToCourse(String courseName);
}
