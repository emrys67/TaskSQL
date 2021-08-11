package com.foxminded.jdbc.services;

import com.foxminded.jdbc.dao.CourseJdbcDao;
import com.foxminded.jdbc.exceptions.UniversityAppException;

import java.io.BufferedReader;
import java.io.IOException;

public class CourseJdbcService implements CourseService{
    private static final String INSERT_COURSE_ID = "Insert course id";
    private static final String INSERT_STUDENT_ID = "Insert student id";
    private static final String STUDENT_HAS_BEEN_ADDED_TO_THE_COURSE = "Student has been added to the course";
    private static final String STUDENT_HAS_NOT_BEEN_ADDED_TO_THE_COURSE = "Student has not been added to the course";
    private static final String BANANASCHOOL_DB_URL = "jdbc:postgresql://localhost:5432/bananaschool";
    private static final String STUDENT_HAS_BEEN_REMOVED_FROM_THE_COURSE = "Student has been removed to the course";
    private static final String STUDENT_HAS_NOT_BEEN_REMOVED_FROM_THE_COURSE = "Student has not been removed to the course";
    public void addStudentToTheCourse(BufferedReader reader){
        System.out.println(CourseJdbcDao.getInstance(BANANASCHOOL_DB_URL).getAllCourses().toString());
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
        CourseJdbcDao.getInstance(BANANASCHOOL_DB_URL).addStudentToTheCourse(studentId, courseId);
        System.out.println(STUDENT_HAS_BEEN_ADDED_TO_THE_COURSE);
    }
    public void removeStudentFromTheCourse(BufferedReader reader){
        System.out.println(INSERT_STUDENT_ID);
        Long studentId;
        Long courseId;
        try {
            studentId = Long.parseLong(reader.readLine());
            System.out.println(CourseJdbcDao.getInstance(BANANASCHOOL_DB_URL).findCoursesRelatedToStudent(studentId).toString());
            System.out.println(INSERT_COURSE_ID);
            courseId = Long.parseLong(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            throw new UniversityAppException(STUDENT_HAS_NOT_BEEN_REMOVED_FROM_THE_COURSE);
        }
        CourseJdbcDao.getInstance(BANANASCHOOL_DB_URL).removeStudentFromCourse(studentId, courseId);
        System.out.println(STUDENT_HAS_BEEN_REMOVED_FROM_THE_COURSE);
    }
}
