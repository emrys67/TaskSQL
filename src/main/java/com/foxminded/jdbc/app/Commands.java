package com.foxminded.jdbc.app;

public interface Commands {
    void findGroupsWithStudentCount();

    void findStudentsRelatedToCourse();

    void addStudent();

    void deleteStudent();

    void addStudentToCourse();

    void removeStudentFromCourse();
}
