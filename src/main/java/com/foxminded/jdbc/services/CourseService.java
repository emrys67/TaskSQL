package com.foxminded.jdbc.services;

import com.foxminded.jdbc.dao.CourseDao;
import com.foxminded.jdbc.entity.Course;
import com.foxminded.jdbc.exceptions.UniversityAppException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class CourseService {
    private static final String STUDENT_HAS_NOT_BEEN_ADDED_TO_THE_COURSE = "Student has not been added to the course";
    private static final String STUDENT_HAS_NOT_BEEN_REMOVED_FROM_THE_COURSE = "Student has not been removed to the course";
    private final CourseDao courseDao;

    public CourseService(CourseDao courseDao) {
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
}
