package com.foxminded.jdbc.services;

import com.foxminded.jdbc.dao.StudentJdbcDao;
import com.foxminded.jdbc.entity.Student;
import com.foxminded.jdbc.exceptions.UniversityAppException;

import java.io.BufferedReader;
import java.io.IOException;

public class StudentJdbcService implements StudentService{
    private static final String INSERT_NAME = "Insert name";
    private static final String INSERT_LASTNAME = "Insert lastname";
    private static final String STUDENT_HAS_BEEN_ADDED = "Student has been added";
    private static final String STUDENT_HAS_NOT_BEEN_ADDED = "Student has not been added";
    private static final String BANANASCHOOL_DB_URL = "jdbc:postgresql://localhost:5432/bananaschool";
    private static final String SPACE = " ";
    private static final String INSERT_COURSE_NAME = "Insert course name";
    private static final String STUDENTS_HAVE_NOT_BEEN_FOUNDED = "Students have not been founded";
    private static final String INSERT_STUDENT_ID = "Insert student id";
    private static final String STUDENT_HAS_BEEN_DELETED = "Student has been deleted";
    private static final String STUDENT_HAS_NOT_BEEN_DELETED = "Student has not been deleted";

    public void addStudent(BufferedReader reader){
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
        StudentJdbcDao.getInstance(BANANASCHOOL_DB_URL).insert(new Student(name, lastname));
        System.out.println(STUDENT_HAS_BEEN_ADDED);
    }
    public void findStudentsRelatedToTheCourse(BufferedReader reader){
        System.out.println(INSERT_COURSE_NAME);
        String courseName;
        try {
            courseName = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            throw new UniversityAppException(STUDENTS_HAVE_NOT_BEEN_FOUNDED);
        }
        StudentJdbcDao.getInstance(BANANASCHOOL_DB_URL).findStudentsRelatedToCourse(courseName).stream().forEach(student ->
                System.out.println(student.getId() + SPACE +
                        student.getGroupId() + SPACE + student.getName() + SPACE + student.getLastname()));
    }
    public void deleteStudent(BufferedReader reader){
        System.out.println(INSERT_STUDENT_ID);
        Long id;
        try {
            id = Long.parseLong(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            throw new UniversityAppException(STUDENT_HAS_NOT_BEEN_DELETED);
        }
        StudentJdbcDao.getInstance(BANANASCHOOL_DB_URL).deleteById(id);
        System.out.println(STUDENT_HAS_BEEN_DELETED);
    }
}
