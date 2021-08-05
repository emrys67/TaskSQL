package com.foxminded.jdbc.dao;

import com.foxminded.jdbc.entity.Group;

import java.util.List;

public interface GroupDao<T> extends Dao<T> {
    List<Group> findGroupsWithStudentCount(int count);
}
