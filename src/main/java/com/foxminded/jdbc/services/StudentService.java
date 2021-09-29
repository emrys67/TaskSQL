package com.foxminded.jdbc.services;

import com.foxminded.jdbc.dao.StudentJdbcDao;
import com.foxminded.jdbc.entity.Student;
import com.foxminded.jdbc.exceptions.UniversityAppException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StudentService {
    private static final String STUDENT_HAS_NOT_BEEN_ADDED = "Student has not been added";
    private static final String STUDENTS_HAVE_NOT_BEEN_FOUNDED = "Students have not been founded";
    private static final String STUDENT_HAS_NOT_BEEN_DELETED = "Student has not been deleted";
    private final StudentJdbcDao studentDao;

    public StudentService(StudentJdbcDao studentDao) {
        this.studentDao = studentDao;
    }

    public void addStudent(BufferedReader reader) {
        try {
            String input = reader.readLine();
            studentDao.insert(new Student(input.substring(0, input.indexOf(" ")),
                    input.substring(input.indexOf(" ") + 1)));
        } catch (IOException e) {
            e.printStackTrace();
            throw new UniversityAppException(STUDENT_HAS_NOT_BEEN_ADDED);
        }
    }

    public List<Student> findStudentsRelatedToTheCourse(BufferedReader reader) {
        String courseName;
        try {
            courseName = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            throw new UniversityAppException(STUDENTS_HAVE_NOT_BEEN_FOUNDED);
        }
        return studentDao.findStudentsRelatedToCourse(courseName);
    }

    public void deleteStudent(BufferedReader reader) {
        try {
            studentDao.deleteById(Long.parseLong(reader.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new UniversityAppException(STUDENT_HAS_NOT_BEEN_DELETED);
        }
    }

    public void createStudents() {
        Random rnd = new Random();
        List<String> names = new ArrayList<>();
        List<String> lastNames = new ArrayList<>();
        names.add("Peter");
        names.add("Adam");
        names.add("Kventin");
        names.add("Andrew");
        names.add("Dmitri");
        names.add("Sakura");
        names.add("Katerina");
        names.add("Evelina");
        names.add("Daria");
        names.add("Timofey");
        lastNames.add("Mask");
        lastNames.add("Tarantino");
        lastNames.add("Merin");
        lastNames.add("Comfy");
        lastNames.add("Tesco");
        lastNames.add("Uchiha");
        lastNames.add("Kva");
        lastNames.add("Mob");
        lastNames.add("Upi");
        lastNames.add("Done");
        for (int i = 0; i < 200; i++) {
            studentDao.insert(new Student(names.get(rnd.nextInt(10)),
                    lastNames.get(rnd.nextInt(10))));
        }
    }

    public void assignStudentsToGroups() {
        for (var i = 1; i < 11; i++) {
            for (var a = 0; a < 10; a++) {
                long studentId = (long) ((Math.random() * (199)) + 1);
                if (studentDao.findById(studentId) != null && studentDao.findById(studentId).getGroupId() == 0) {
                    studentDao.setGroup(studentId, (long) i);
                }
            }
        }
        for (var i = 0; i < 100; i++) {
            long studentId = (long) ((Math.random() * (199)) + 1);
            long groupId = (long) ((Math.random() * (9)) + 1);
            if (studentDao.findById(studentId) != null && studentDao.findById(studentId).getGroupId() == 0) {
                studentDao.setGroup(studentId, groupId);
            }
        }
    }
}
