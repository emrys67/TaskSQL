package com.foxminded.jdbc.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class UniversityApp {
    private static final String WRONG_INPUT = "Wrong output";
    private static final String MENU = """
            Print char from a to f to choose
                a. Find all groups with less or equals student count                                                  
                b. Find all students related to course with given name                                               
                c. Add new student                                       
                d. Delete student by STUDENT_ID                   
                e. Add a student to the course (from a list)                                                  
                f. Remove the student from one of his or her courses""";
    private Commands commands;
    private DatabaseConfiguration configuration;


    public UniversityApp(Commands commands, DatabaseConfiguration configuration) {
        this.commands = commands;
        this.configuration = configuration;
    }

    public void startApp() {
        configuration.configure();
        String input;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println(MENU);
            input = reader.readLine();
            processInput(input);
        } catch (IOException e) {
            throw new UniversityAppException(e);
        }
    }

    void processInput(String input) {
        if (input.equals("a")) {
            findGroupsWithStudentCount();
        } else if (input.equals("b")) {
            findStudentsRelatedToCourse();
        } else if (input.equals("c")) {
            addStudent();
        } else if (input.equals("d")) {
            deleteStudent();
        } else if (input.equals("e")) {
            addStudentToCourse();
        } else if (input.equals("f")) {
            removeStudentFromCourse();
        } else if (!input.equals("g")) {
            throw new UniversityAppException(WRONG_INPUT);
        }
    }

    public void findGroupsWithStudentCount() {
        commands.findGroupsWithStudentCount();
    }

    public void findStudentsRelatedToCourse() {
        commands.findStudentsRelatedToCourse();
    }

    public void addStudent() {
        commands.addStudent();
    }

    public void deleteStudent() {
        commands.deleteStudent();
    }

    public void addStudentToCourse() {
        commands.addStudentToCourse();
    }

    public void removeStudentFromCourse() {
        commands.removeStudentFromCourse();
    }

    public void setCommands(Commands commands) {
        this.commands = commands;
    }

    public void setConfiguration(DatabaseConfiguration configuration) {
        this.configuration = configuration;
    }
}
