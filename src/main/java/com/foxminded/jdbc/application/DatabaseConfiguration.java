package com.foxminded.jdbc.application;

import com.foxminded.jdbc.dao.CourseJdbcDao;
import com.foxminded.jdbc.dao.GroupJdbcDao;
import com.foxminded.jdbc.dao.StudentJdbcDao;
import com.foxminded.jdbc.entity.Course;
import com.foxminded.jdbc.entity.Group;
import com.foxminded.jdbc.entity.Student;
import com.foxminded.jdbc.readers.SqlScriptRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DatabaseConfiguration {
    private static final String BANANASCHOOL_DB_URL = "jdbc:postgresql://localhost:5432/bananaschool";
    private static final String DDL_FILE_PATH = "src/main/resources/DDLfile.sql";
    private static final String DML_FILE_PATH = "src/main/resources/tablescreation.sql";
    private static final String HYPHEN = "-";
    private CourseJdbcDao courseJdbcDao;
    private GroupJdbcDao groupJdbcDao;
    private StudentJdbcDao studentJdbcDao;

    public DatabaseConfiguration(CourseJdbcDao courseJdbcDao, GroupJdbcDao groupJdbcDao, StudentJdbcDao studentJdbcDao) {
        this.courseJdbcDao = courseJdbcDao;
        this.groupJdbcDao = groupJdbcDao;
        this.studentJdbcDao = studentJdbcDao;
    }

    public void configure() {
        SqlScriptRunner scriptRunner = new SqlScriptRunner();
        scriptRunner.executeAllScripts(DDL_FILE_PATH, DML_FILE_PATH, BANANASCHOOL_DB_URL);
        fillInData();
    }

    private void fillInData() {
        createGroups();
        createCourses();
        createStudents();
        assignStudentsToGroups();
        assignCourses();
    }

    private void createGroups() {
        for (int i = 0; i < 10; i++) {
            Random rnd = new Random();
            String firstChar = String.valueOf((char) ('a' + rnd.nextInt(26)));
            String secondChar = String.valueOf((char) ('a' + rnd.nextInt(26)));
            int firstInt = rnd.nextInt(9);
            int secondInt = rnd.nextInt(9);
            String name = firstChar + secondChar + HYPHEN + firstInt + secondInt;
            groupJdbcDao.insert(new Group(name));
        }
    }

    private void createCourses() {
        courseJdbcDao.insert(new Course("math", "onion"));
        courseJdbcDao.insert(new Course("oop", "fish"));
        courseJdbcDao.insert(new Course("biology", "potatto"));
        courseJdbcDao.insert(new Course("history", "spagetti"));
        courseJdbcDao.insert(new Course("english", "nudles"));
        courseJdbcDao.insert(new Course("spanish", "kebab"));
        courseJdbcDao.insert(new Course("russian", "pizza"));
        courseJdbcDao.insert(new Course("sleeping", "bananafish"));
        courseJdbcDao.insert(new Course("economy", "fingers"));
        courseJdbcDao.insert(new Course("algorithms", "candy"));
    }

    private void createStudents() {
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
            studentJdbcDao.insert(new Student(names.get(rnd.nextInt(10)),
                    lastNames.get(rnd.nextInt(10))));
        }
    }

    private void assignStudentsToGroups() {
        for (var i = 1; i < 11; i++) {
            for (var a = 0; a < 10; a++) {
                long studentId = (long) ((Math.random() * (199)) + 1);
                if (studentJdbcDao.findById(studentId).getGroupId() == 0) {
                    studentJdbcDao.setGroup(studentId, (long) i);
                }
            }
        }
        for (var i = 0; i < 100; i++) {
            long studentId = (long) ((Math.random() * (199)) + 1);
            long groupId = (long) ((Math.random() * (9)) + 1);
            if (studentJdbcDao.findById(studentId).getGroupId() == 0) {
                studentJdbcDao.setGroup(studentId, groupId);
            }
        }
    }

    private void assignCourses() {
        long courseId;
        int repeats;
        for (var i = 1; i < 201; i++) {
            repeats = (int) ((Math.random() * (2)) + 1);
            for (var a = 0; a < repeats; a++) {
                courseId = (long) ((Math.random() * (9)) + 1);
                courseJdbcDao.addStudentToTheCourse((long) i, courseId);
            }
        }
    }
}
