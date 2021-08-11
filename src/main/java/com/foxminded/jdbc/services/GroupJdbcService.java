package com.foxminded.jdbc.services;

import com.foxminded.jdbc.dao.GroupJdbcDao;
import com.foxminded.jdbc.exceptions.UniversityAppException;

import java.io.BufferedReader;
import java.io.IOException;

public class GroupJdbcService implements GroupService{
    private static final String SPACE = " ";
    private static final String INSERT_COUNT_OF_STUDENTS = "Insert count of students";
    private static final String GROUPS_HAVE_NOT_BEEN_FOUNDED = "Groups have not been founded";
    private static final String BANANASCHOOL_DB_URL = "jdbc:postgresql://localhost:5432/bananaschool";
    public void findGroupsWithStudentCount(BufferedReader reader){
        System.out.println(INSERT_COUNT_OF_STUDENTS);
        int count;
        try {
            count = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            throw new UniversityAppException(GROUPS_HAVE_NOT_BEEN_FOUNDED);
        }
        GroupJdbcDao.getInstance(BANANASCHOOL_DB_URL).findGroupsWithStudentCount(count).stream().forEach(group ->
                System.out.println(group.getId() + SPACE + group.getName()));
    }
}
