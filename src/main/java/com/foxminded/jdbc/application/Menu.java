package com.foxminded.jdbc.application;

import java.io.BufferedReader;

public interface Menu {
    void findGroupsWithStudentCount(BufferedReader reader);

    void findStudentsRelatedToCourse(BufferedReader reader);

    void addStudent(BufferedReader reader);

    void deleteStudent(BufferedReader reader);

    void addStudentToCourse(BufferedReader reader);

    void removeStudentFromCourse(BufferedReader reader);
}
