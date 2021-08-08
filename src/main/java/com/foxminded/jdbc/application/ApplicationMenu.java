package com.foxminded.jdbc.application;

import com.foxminded.jdbc.exceptions.UniversityAppException;
import com.foxminded.jdbc.services.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ApplicationMenu implements Menu {
    private static final String WRONG_INPUT = "Wrong output";
    private static final String MENU = """
            Print char from a to f to choose
                a. Find all groups with less or equals student count                                                  
                b. Find all students related to course with given name                                               
                c. Add new student                                       
                d. Delete student by STUDENT_ID                   
                e. Add a student to the course (from a list)                                                  
                f. Remove the student from one of his or her courses
                g. Exit""";
    private Service addStudent;
    private Service addStudentToTheCourse;
    private Service deleteStudent;
    private Service findGroups;
    private Service findStudents;
    private Service removeStudentFromCourse;

    public ApplicationMenu(Service addStudent, Service addStudentToTheCourse, Service deleteStudent, Service findGroups, Service findStudents, Service removeStudentFromCourse) {
        this.addStudent = addStudent;
        this.addStudentToTheCourse = addStudentToTheCourse;
        this.deleteStudent = deleteStudent;
        this.findGroups = findGroups;
        this.findStudents = findStudents;
        this.removeStudentFromCourse = removeStudentFromCourse;
    }

    public void executeMenu() {
        String input = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.println(MENU);
                input = reader.readLine();
                if (input.equals("a")) {
                    findGroupsWithStudentCount(reader);
                } else if (input.equals("b")) {
                    findStudentsRelatedToCourse(reader);
                } else if (input.equals("c")) {
                    addStudent(reader);
                } else if (input.equals("d")) {
                    deleteStudent(reader);
                } else if (input.equals("e")) {
                    addStudentToCourse(reader);
                } else if (input.equals("f")) {
                    removeStudentFromCourse(reader);
                } else if (input.equals("g")) {
                    break;
                } else {
                    throw new UniversityAppException(WRONG_INPUT);
                }
            }
        } catch (IOException e) {
            throw new UniversityAppException(e);
        }
    }

    public void findGroupsWithStudentCount(BufferedReader reader) {
        findGroups.serve(reader);
    }

    public void findStudentsRelatedToCourse(BufferedReader reader) {
        findStudents.serve(reader);
    }

    public void addStudent(BufferedReader reader) {
        addStudent.serve(reader);
    }

    public void deleteStudent(BufferedReader reader) {
        deleteStudent.serve(reader);
    }

    public void addStudentToCourse(BufferedReader reader) {
        addStudentToTheCourse.serve(reader);
    }

    public void removeStudentFromCourse(BufferedReader reader) {
        removeStudentFromCourse.serve(reader);
    }

    public Service getAddStudent() {
        return addStudent;
    }

    public void setAddStudent(Service addStudent) {
        this.addStudent = addStudent;
    }

    public Service getAddStudentToTheCourse() {
        return addStudentToTheCourse;
    }

    public void setAddStudentToTheCourse(Service addStudentToTheCourse) {
        this.addStudentToTheCourse = addStudentToTheCourse;
    }

    public Service getDeleteStudent() {
        return deleteStudent;
    }

    public void setDeleteStudent(Service deleteStudent) {
        this.deleteStudent = deleteStudent;
    }

    public Service getFindGroups() {
        return findGroups;
    }

    public void setFindGroups(Service findGroups) {
        this.findGroups = findGroups;
    }

    public Service getFindStudents() {
        return findStudents;
    }

    public void setFindStudents(Service findStudents) {
        this.findStudents = findStudents;
    }

    public Service getRemoveStudentFromCourse() {
        return removeStudentFromCourse;
    }

    public void setRemoveStudentFromCourse(Service removeStudentFromCourse) {
        this.removeStudentFromCourse = removeStudentFromCourse;
    }
}
