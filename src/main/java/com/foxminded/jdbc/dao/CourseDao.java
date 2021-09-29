package com.foxminded.jdbc.dao;

import com.foxminded.jdbc.entity.Course;

import java.util.List;

public interface CourseDao<T> extends Dao<T> {
    boolean addStudentToTheCourse(Long studentId, Long courseId);

    List<Course> findCoursesRelatedToStudent(Long studentName);

    boolean removeStudentFromCourse(Long studentId, Long courseId);

    List<Course> getAllCourses();
}
