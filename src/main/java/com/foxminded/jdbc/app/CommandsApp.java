package com.foxminded.jdbc.app;

import com.foxminded.jdbc.dao.CourseJdbcDao;
import com.foxminded.jdbc.dao.GroupJdbcDao;
import com.foxminded.jdbc.dao.StudentJdbcDao;
import com.foxminded.jdbc.entity.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandsApp implements Commands {
    private static final String INSERT_COUNT_OF_STUDENTS = "Insert count of students";
    private static final String GROUPS_HAVE_NOT_BEEN_FOUNDED = "Groups have not been founded";
    private static final String INSERT_COURSE_NAME = "Insert course name";
    private static final String STUDENTS_HAVE_NOT_BEEN_FOUNDED = "Students have not been founded";
    private static final String INSERT_NAME = "Insert name";
    private static final String INSERT_LASTNAME = "Insert lastname";
    private static final String STUDENT_HAS_BEEN_ADDED = "Student has been added";
    private static final String STUDENT_HAS_NOT_BEEN_ADDED = "Student has not been added";
    private static final String INSERT_STUDENT_ID = "Insert student id";
    private static final String STUDENT_HAS_BEEN_DELETED = "Student has been deleted";
    private static final String STUDENT_HAS_NOT_BEEN_DELETED = "Student has not been deleted";
    private static final String INSERT_COURSE_ID = "Insert course id";
    private static final String STUDENT_HAS_BEEN_ADDED_TO_THE_COURSE = "Student has been added to the course";
    private static final String STUDENT_HAS_NOT_BEEN_ADDED_TO_THE_COURSE = "Student has not been added to the course";
    private static final String STUDENT_HAS_BEEN_REMOVED_FROM_THE_COURSE = "Student has been removed to the course";
    private static final String STUDENT_HAS_NOT_BEEN_REMOVED_FROM_THE_COURSE = "Student has not been removed to the course";
    private static final String SPACE = " ";

    public void findGroupsWithStudentCount() {
        System.out.println(INSERT_COUNT_OF_STUDENTS);
        int count;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            count = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            throw new UniversityAppException(GROUPS_HAVE_NOT_BEEN_FOUNDED);
        }
        GroupJdbcDao.getInstance().findGroupsWithStudentCount(count).stream().forEach(group ->
                System.out.println(group.getId() + SPACE + group.getName()));
    }

    public void findStudentsRelatedToCourse() {
        System.out.println(INSERT_COURSE_NAME);
        String courseName;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            courseName = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            throw new UniversityAppException(STUDENTS_HAVE_NOT_BEEN_FOUNDED);
        }
        StudentJdbcDao.getInstance().findStudentsRelatedToCourse(courseName).stream().forEach(student ->
                System.out.println(student.getId() + SPACE +
                        student.getGroupId() + " " + student.getName() + SPACE + student.getLastname()));
    }

    public void addStudent() {
        String name;
        String lastname;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println(INSERT_NAME);
            name = reader.readLine();
            System.out.println(INSERT_LASTNAME);
            lastname = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            throw new UniversityAppException(STUDENT_HAS_NOT_BEEN_ADDED);
        }
        StudentJdbcDao.getInstance().insert(new Student(name, lastname));
        System.out.println(STUDENT_HAS_BEEN_ADDED);
    }

    public void deleteStudent() {
        System.out.println(INSERT_STUDENT_ID);
        Long id;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            id = Long.parseLong(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            throw new UniversityAppException(STUDENT_HAS_NOT_BEEN_DELETED);
        }
        StudentJdbcDao.getInstance().deleteById(id);
        System.out.println(STUDENT_HAS_BEEN_DELETED);
    }

    public void addStudentToCourse() {
        System.out.println(CourseJdbcDao.getInstance().getAllCourses().toString());
        System.out.println(INSERT_STUDENT_ID);
        long studentId;
        System.out.println(INSERT_COURSE_ID);
        long courseId;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            studentId = Long.parseLong(reader.readLine());
            courseId = Long.parseLong(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            throw new UniversityAppException(STUDENT_HAS_NOT_BEEN_ADDED_TO_THE_COURSE);
        }
        CourseJdbcDao.getInstance().addStudentToTheCourse(studentId, courseId);
        System.out.println(STUDENT_HAS_BEEN_ADDED_TO_THE_COURSE);
    }

    public void removeStudentFromCourse() {
        System.out.println(INSERT_STUDENT_ID);
        Long studentId;
        Long courseId;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            studentId = Long.parseLong(reader.readLine());
            System.out.println(CourseJdbcDao.getInstance().findCoursesRelatedToStudent(studentId).toString());
            System.out.println(INSERT_COURSE_ID);
            courseId = Long.parseLong(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            throw new UniversityAppException(STUDENT_HAS_NOT_BEEN_REMOVED_FROM_THE_COURSE);
        }
        CourseJdbcDao.getInstance().removeStudentFromCourse(studentId, courseId);
        System.out.println(STUDENT_HAS_BEEN_REMOVED_FROM_THE_COURSE);
    }
}
