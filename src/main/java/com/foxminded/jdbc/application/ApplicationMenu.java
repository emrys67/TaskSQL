package com.foxminded.jdbc.application;

import com.foxminded.jdbc.exceptions.UniversityAppException;
import com.foxminded.jdbc.services.CourseService;
import com.foxminded.jdbc.services.GroupService;
import com.foxminded.jdbc.services.StudentService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ApplicationMenu implements Menu {
    private static final String SPACE = " ";
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
    private static final String INSERT_NAME_AND_LASTNAME = "Insert \"name\" [ENTER] \"lastname\"";
    private static final String STUDENT_HAS_BEEN_ADDED = "Student has been added";
    private static final String INSERT_COURSE_NAME = "Insert course name";
    private static final String STUDENT_HAS_BEEN_DELETED = "Student has been deleted";
    private static final String INSERT_STUDENT_ID = "Insert student id";
    private static final String INSERT_COUNT_OF_STUDENTS = "Insert count of students";
    private static final String INSERT_STUDENT_AND_COURSE_ID = "Insert \"studentID\"[ENTER]\"courseID\"";
    private static final String STUDENT_HAS_BEEN_ADDED_TO_THE_COURSE = "Student has been added to the course";
    private static final String INSERT_COURSE_ID = "Insert course id";
    private final StudentService studentService;
    private final CourseService courseService;
    private final GroupService groupService;

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
        System.out.println(INSERT_COUNT_OF_STUDENTS);
        groupService.findGroupsWithStudentCount(reader).stream().forEach(group ->
                System.out.println(group.getId() + SPACE + group.getName()));
    }

    public void findStudentsRelatedToCourse(BufferedReader reader) {
        System.out.println(INSERT_COURSE_NAME);
        studentService.findStudentsRelatedToTheCourse(reader).stream().forEach(student ->
                System.out.println(student.getId() + SPACE +
                        student.getGroupId() + SPACE + student.getName() + SPACE + student.getLastname()));
    }

    public void addStudent(BufferedReader reader) {
        System.out.println(INSERT_NAME_AND_LASTNAME);
        studentService.addStudent(reader);
        System.out.println(STUDENT_HAS_BEEN_ADDED);
    }

    public void deleteStudent(BufferedReader reader) {
        System.out.println(INSERT_STUDENT_ID);
        studentService.deleteStudent(reader);
        System.out.println(STUDENT_HAS_BEEN_DELETED);
    }

    public void addStudentToCourse(BufferedReader reader) {
        System.out.println(courseService.getAllCourses().toString());
        System.out.println(INSERT_STUDENT_AND_COURSE_ID);
        courseService.addStudentToTheCourse(reader);
        System.out.println(STUDENT_HAS_BEEN_ADDED_TO_THE_COURSE);
    }

    public void removeStudentFromCourse(BufferedReader reader) {
        System.out.println(INSERT_STUDENT_ID);
        try {
            long studentId = Long.parseLong(reader.readLine());
            System.out.println(courseService.findCoursesRelatedToStudent(studentId).toString());
            System.out.println(INSERT_COURSE_ID);
            courseService.removeStudentFromTheCourse(reader, studentId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
