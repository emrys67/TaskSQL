package com.foxminded.jdbc.services;

import com.foxminded.jdbc.dao.CourseJdbcDao;
import com.foxminded.jdbc.entity.Course;
import com.foxminded.jdbc.exceptions.UniversityAppException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class CourseService {
    private static final String STUDENT_HAS_NOT_BEEN_ADDED_TO_THE_COURSE = "Student has not been added to the course";
    private static final String STUDENT_HAS_NOT_BEEN_REMOVED_FROM_THE_COURSE = "Student has not been removed to the course";
    private final CourseJdbcDao courseJdbcDao;

    public CourseService(CourseJdbcDao courseJdbcDao) {
        this.courseJdbcDao = courseJdbcDao;
    }

    public void addStudentToTheCourse(BufferedReader reader) {
        String input;
        try {
            long studentId = Long.parseLong(reader.readLine());
            long courseId = Long.parseLong(reader.readLine());
            courseJdbcDao.addStudentToTheCourse(studentId, courseId);
        } catch (IOException e) {
            e.printStackTrace();
            throw new UniversityAppException(STUDENT_HAS_NOT_BEEN_ADDED_TO_THE_COURSE);
        }
    }

    public void removeStudentFromTheCourse(BufferedReader reader, long studentId) {
        try {
            long courseId = Long.parseLong(reader.readLine());
            courseJdbcDao.removeStudentFromCourse(studentId, courseId);
        } catch (IOException e) {
            e.printStackTrace();
            throw new UniversityAppException(STUDENT_HAS_NOT_BEEN_REMOVED_FROM_THE_COURSE);
        }
    }

    public List<Course> findCoursesRelatedToStudent(Long studentId) {
        return courseJdbcDao.findCoursesRelatedToStudent(studentId);
    }

    public List<Course> getAllCourses() {
        return courseJdbcDao.getAllCourses();
    }
}
