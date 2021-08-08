package com.foxminded.jdbc.services;

import com.foxminded.jdbc.exceptions.UniversityAppException;
import com.foxminded.jdbc.dao.CourseJdbcDao;

import java.io.BufferedReader;
import java.io.IOException;

public class AddStudentToCourseService implements Service {
    private static final String INSERT_COURSE_ID = "Insert course id";
    private static final String INSERT_STUDENT_ID = "Insert student id";
    private static final String STUDENT_HAS_BEEN_ADDED_TO_THE_COURSE = "Student has been added to the course";
    private static final String STUDENT_HAS_NOT_BEEN_ADDED_TO_THE_COURSE = "Student has not been added to the course";
    private static final String BANANASCHOOL_DB = "bananaschool";

    public void serve(BufferedReader reader) {
        System.out.println(CourseJdbcDao.getInstance(BANANASCHOOL_DB).getAllCourses().toString());
        System.out.println(INSERT_STUDENT_ID);
        long studentId;
        System.out.println(INSERT_COURSE_ID);
        long courseId;
        try {
            studentId = Long.parseLong(reader.readLine());
            courseId = Long.parseLong(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            throw new UniversityAppException(STUDENT_HAS_NOT_BEEN_ADDED_TO_THE_COURSE);
        }
        CourseJdbcDao.getInstance(BANANASCHOOL_DB).addStudentToTheCourse(studentId, courseId);
        System.out.println(STUDENT_HAS_BEEN_ADDED_TO_THE_COURSE);
    }
}
