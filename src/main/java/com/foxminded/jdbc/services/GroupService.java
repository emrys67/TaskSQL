package com.foxminded.jdbc.services;

import com.foxminded.jdbc.dao.GroupJdbcDao;
import com.foxminded.jdbc.entity.Group;
import com.foxminded.jdbc.exceptions.UniversityAppException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class GroupService {
    private static final String GROUPS_HAVE_NOT_BEEN_FOUNDED = "Groups have not been founded";
    private final GroupJdbcDao groupJdbcDao;

    public GroupService(GroupJdbcDao groupJdbcDao) {
        this.groupJdbcDao = groupJdbcDao;
    }

    public List<Group> findGroupsWithStudentCount(BufferedReader reader) {
        try {
            return groupJdbcDao.findGroupsWithStudentCount(Integer.parseInt(reader.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new UniversityAppException(GROUPS_HAVE_NOT_BEEN_FOUNDED);
        }
    }
}
