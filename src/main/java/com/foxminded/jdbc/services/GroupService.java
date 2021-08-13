package com.foxminded.jdbc.services;

import com.foxminded.jdbc.dao.GroupDao;
import com.foxminded.jdbc.entity.Group;
import com.foxminded.jdbc.exceptions.UniversityAppException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class GroupService {
    private static final String GROUPS_HAVE_NOT_BEEN_FOUNDED = "Groups have not been founded";
    private final GroupDao groupDao;

    public GroupService(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public List<Group> findGroupsWithStudentCount(BufferedReader reader) {
        try {
            return groupDao.findGroupsWithStudentCount(Integer.parseInt(reader.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new UniversityAppException(GROUPS_HAVE_NOT_BEEN_FOUNDED);
        }
    }
}
