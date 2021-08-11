package com.foxminded.jdbc.application;

import com.foxminded.jdbc.exceptions.UniversityAppException;
import com.foxminded.jdbc.services.CourseService;
import com.foxminded.jdbc.services.GroupService;
import com.foxminded.jdbc.services.StudentService;

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
    private StudentService studentService;
    private CourseService courseService;
    private GroupService groupService;

    public ApplicationMenu(StudentService studentService, CourseService courseService, GroupService groupService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.groupService = groupService;
    }

    public void executeMenu() {
        String input;
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
        groupService.findGroupsWithStudentCount(reader);
    }

    public void findStudentsRelatedToCourse(BufferedReader reader) {
        studentService.findStudentsRelatedToTheCourse(reader);
    }

    public void addStudent(BufferedReader reader) {
        studentService.addStudent(reader);
    }

    public void deleteStudent(BufferedReader reader) {
        studentService.deleteStudent(reader);
    }

    public void addStudentToCourse(BufferedReader reader) {
        courseService.addStudentToTheCourse(reader);
    }

    public void removeStudentFromCourse(BufferedReader reader) {
        courseService.removeStudentFromTheCourse(reader);
    }
}
