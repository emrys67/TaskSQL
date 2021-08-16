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
    private final CourseJdbcDao courseDao;

    public CourseService(CourseJdbcDao courseDao) {
        this.courseDao = courseDao;
    }

    public void addStudentToTheCourse(BufferedReader reader) {
        try {
            long studentId = Long.parseLong(reader.readLine());
            long courseId = Long.parseLong(reader.readLine());
            courseDao.addStudentToTheCourse(studentId, courseId);
        } catch (IOException e) {
            e.printStackTrace();
            throw new UniversityAppException(STUDENT_HAS_NOT_BEEN_ADDED_TO_THE_COURSE);
        }
    }

    public void removeStudentFromTheCourse(BufferedReader reader, long studentId) {
        try {
            long courseId = Long.parseLong(reader.readLine());
            courseDao.removeStudentFromCourse(studentId, courseId);
        } catch (IOException e) {
            e.printStackTrace();
            throw new UniversityAppException(STUDENT_HAS_NOT_BEEN_REMOVED_FROM_THE_COURSE);
        }
    }

    public List<Course> findCoursesRelatedToStudent(Long studentId) {
        return courseDao.findCoursesRelatedToStudent(studentId);
    }

    public List<Course> getAllCourses() {
        return courseDao.getAllCourses();
    }

    public void createCourses() {
        courseDao.insert(new Course("math", "onion"));
        courseDao.insert(new Course("oop", "fish"));
        courseDao.insert(new Course("biology", "potatto"));
        courseDao.insert(new Course("history", "spagetti"));
        courseDao.insert(new Course("english", "nudles"));
        courseDao.insert(new Course("spanish", "kebab"));
        courseDao.insert(new Course("russian", "pizza"));
        courseDao.insert(new Course("sleeping", "bananafish"));
        courseDao.insert(new Course("economy", "fingers"));
        courseDao.insert(new Course("algorithms", "candy"));
    }

    public void assignCourses() {
        long courseId;
        int repeats;
        for (var i = 1; i < 201; i++) {
            repeats = (int) ((Math.random() * (2)) + 1);
            for (var a = 0; a < repeats; a++) {
                courseId = (long) ((Math.random() * (9)) + 1);
                courseDao.addStudentToTheCourse((long) i, courseId);
            }
        }
    }
}
