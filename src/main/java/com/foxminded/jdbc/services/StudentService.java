package com.foxminded.jdbc.services;

import java.io.BufferedReader;

public interface StudentService {
    void addStudent(BufferedReader reader);
    void findStudentsRelatedToTheCourse(BufferedReader reader);
    void deleteStudent(BufferedReader reader);
}
