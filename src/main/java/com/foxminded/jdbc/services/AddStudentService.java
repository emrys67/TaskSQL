package com.foxminded.jdbc.services;

import com.foxminded.jdbc.exceptions.UniversityAppException;
import com.foxminded.jdbc.dao.StudentJdbcDao;
import com.foxminded.jdbc.entity.Student;

import java.io.BufferedReader;
import java.io.IOException;

public class AddStudentService implements Service {
    private static final String INSERT_NAME = "Insert name";
    private static final String INSERT_LASTNAME = "Insert lastname";
    private static final String STUDENT_HAS_BEEN_ADDED = "Student has been added";
    private static final String STUDENT_HAS_NOT_BEEN_ADDED = "Student has not been added";
    private static final String BANANASCHOOL_DB = "bananaschool";

    public void serve(BufferedReader reader) {
        String name;
        String lastname;
        try {
            System.out.println(INSERT_NAME);
            name = reader.readLine();
            System.out.println(INSERT_LASTNAME);
            lastname = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            throw new UniversityAppException(STUDENT_HAS_NOT_BEEN_ADDED);
        }
        StudentJdbcDao.getInstance(BANANASCHOOL_DB).insert(new Student(name, lastname));
        System.out.println(STUDENT_HAS_BEEN_ADDED);
    }
}
