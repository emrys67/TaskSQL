package com.foxminded.jdbc.services;

import java.io.BufferedReader;

public interface CourseService {
    void removeStudentFromTheCourse(BufferedReader reader);
    void addStudentToTheCourse(BufferedReader reader);
}
