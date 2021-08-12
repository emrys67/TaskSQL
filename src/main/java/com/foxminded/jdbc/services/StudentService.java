package com.foxminded.jdbc.services;

import com.foxminded.jdbc.dao.StudentJdbcDao;
import com.foxminded.jdbc.entity.Student;
import com.foxminded.jdbc.exceptions.UniversityAppException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class StudentService {
    private static final String STUDENT_HAS_NOT_BEEN_ADDED = "Student has not been added";
    private static final String STUDENTS_HAVE_NOT_BEEN_FOUNDED = "Students have not been founded";
    private static final String STUDENT_HAS_NOT_BEEN_DELETED = "Student has not been deleted";
    private final StudentJdbcDao studentJdbcDao;

    public StudentService(StudentJdbcDao studentJdbcDao) {
        this.studentJdbcDao = studentJdbcDao;
    }

    public void addStudent(BufferedReader reader) {
        try {
            String input = reader.readLine();
            studentJdbcDao.insert(new Student(input.substring(0, input.indexOf(" ") - 1),
                    input.substring(input.indexOf(" ")) + 1));
        } catch (IOException e) {
            e.printStackTrace();
            throw new UniversityAppException(STUDENT_HAS_NOT_BEEN_ADDED);
        }
    }

    public List<Student> findStudentsRelatedToTheCourse(BufferedReader reader) {
        String courseName;
        try {
            courseName = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            throw new UniversityAppException(STUDENTS_HAVE_NOT_BEEN_FOUNDED);
        }
        return studentJdbcDao.findStudentsRelatedToCourse(courseName);
    }

    public void deleteStudent(BufferedReader reader) {
        try {
            studentJdbcDao.deleteById(Long.parseLong(reader.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new UniversityAppException(STUDENT_HAS_NOT_BEEN_DELETED);
        }
    }
}
