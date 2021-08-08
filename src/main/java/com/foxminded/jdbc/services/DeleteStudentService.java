package com.foxminded.jdbc.services;

import com.foxminded.jdbc.exceptions.UniversityAppException;
import com.foxminded.jdbc.dao.StudentJdbcDao;

import java.io.BufferedReader;
import java.io.IOException;

public class DeleteStudentService implements Service {
    private static final String INSERT_STUDENT_ID = "Insert student id";
    private static final String STUDENT_HAS_BEEN_DELETED = "Student has been deleted";
    private static final String STUDENT_HAS_NOT_BEEN_DELETED = "Student has not been deleted";

    public void serve(BufferedReader reader) {
        System.out.println(INSERT_STUDENT_ID);
        Long id;
        try {
            id = Long.parseLong(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            throw new UniversityAppException(STUDENT_HAS_NOT_BEEN_DELETED);
        }
        StudentJdbcDao.getInstance().deleteById(id);
        System.out.println(STUDENT_HAS_BEEN_DELETED);
    }
}
