package com.foxminded.jdbc.services;

import com.foxminded.jdbc.exceptions.UniversityAppException;
import com.foxminded.jdbc.dao.StudentJdbcDao;

import java.io.BufferedReader;
import java.io.IOException;

public class FindStudentsRelatedToTheCourseService implements Service {
    private static final String SPACE = " ";
    private static final String INSERT_COURSE_NAME = "Insert course name";
    private static final String STUDENTS_HAVE_NOT_BEEN_FOUNDED = "Students have not been founded";
    private static final String BANANASCHOOL_DB = "bananaschool";

    public void serve(BufferedReader reader) {
        System.out.println(INSERT_COURSE_NAME);
        String courseName;
        try {
            courseName = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            throw new UniversityAppException(STUDENTS_HAVE_NOT_BEEN_FOUNDED);
        }
        StudentJdbcDao.getInstance(BANANASCHOOL_DB).findStudentsRelatedToCourse(courseName).stream().forEach(student ->
                System.out.println(student.getId() + SPACE +
                        student.getGroupId() + SPACE + student.getName() + SPACE + student.getLastname()));
    }
}
