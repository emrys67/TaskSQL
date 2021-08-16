package com.foxminded.jdbc.services;

import com.foxminded.jdbc.dao.GroupJdbcDao;
import com.foxminded.jdbc.entity.Group;
import com.foxminded.jdbc.exceptions.UniversityAppException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class GroupService {
    private static final String GROUPS_HAVE_NOT_BEEN_FOUNDED = "Groups have not been founded";
    private static final String HYPHEN = "-";
    private final GroupJdbcDao groupDao;

    public GroupService(GroupJdbcDao groupDao) {
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
    public void createGroups() {
        for (int i = 0; i < 10; i++) {
            Random rnd = new Random();
            String firstChar = String.valueOf((char) ('a' + rnd.nextInt(26)));
            String secondChar = String.valueOf((char) ('a' + rnd.nextInt(26)));
            int firstInt = rnd.nextInt(9);
            int secondInt = rnd.nextInt(9);
            String name = firstChar + secondChar + HYPHEN + firstInt + secondInt;
            groupDao.insert(new Group(name));
        }
    }
}
