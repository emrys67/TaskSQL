package com.foxminded.jdbc.app;

import com.foxminded.jdbc.connection.ConnectionManager;
import com.foxminded.jdbc.dao.CourseJdbcDao;
import com.foxminded.jdbc.dao.GroupJdbcDao;
import com.foxminded.jdbc.dao.StudentJdbcDao;
import com.foxminded.jdbc.entity.Course;
import com.foxminded.jdbc.entity.Group;
import com.foxminded.jdbc.entity.Student;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DatabaseConfiguration {
    private static final String BANANABASE_DB = "bananabase";
    private static final String BANANASCHOOL_DB = "bananaschool";
    private static final String DDL_FILE_PATH = "src/main/resources/DDLfile.sql";
    private static final String DML_FILE_PATH = "src/main/resources/tablescreation.sql";
    private static final String HYPHEN = "-";
    private static final String EXCEPTION_SQL = "DatabaseConfiguration sql exception";

    public void configure() {
        runDdlScript();
        runDmlSript();
        fillInData();
    }

    private void runDdlScript() {
        StringBuilder toRun = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(DDL_FILE_PATH));
             Connection connectionOne = ConnectionManager.open(BANANABASE_DB)) {
            while (reader.ready()) {
                toRun.append(reader.readLine());
            }
            var statement = connectionOne.createStatement();
            statement.execute(toRun.toString());
            statement.close();
        } catch (SQLException | IOException throwables) {
            throw new UniversityAppException(EXCEPTION_SQL);
        }
    }

    private void runDmlSript() {
        StringBuilder toRun = new StringBuilder();
        try (BufferedReader secondReader = new BufferedReader(new FileReader(DML_FILE_PATH));
             var connection = ConnectionManager.open(BANANASCHOOL_DB)) {
            while (secondReader.ready()) {
                toRun.append(secondReader.readLine());
            }
            var statement = connection.createStatement();
            statement.execute(toRun.toString());
            statement.close();
        } catch (SQLException | IOException throwables) {
            throw new UniversityAppException(EXCEPTION_SQL);
        }
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
            GroupJdbcDao.getInstance().insert(new Group(name));
        }
    }

    private void createCourses() {
        CourseJdbcDao.getInstance().insert(new Course("math", "onion"));
        CourseJdbcDao.getInstance().insert(new Course("oop", "fish"));
        CourseJdbcDao.getInstance().insert(new Course("biology", "potatto"));
        CourseJdbcDao.getInstance().insert(new Course("history", "spagetti"));
        CourseJdbcDao.getInstance().insert(new Course("english", "nudles"));
        CourseJdbcDao.getInstance().insert(new Course("spanish", "kebab"));
        CourseJdbcDao.getInstance().insert(new Course("russian", "pizza"));
        CourseJdbcDao.getInstance().insert(new Course("sleeping", "bananafish"));
        CourseJdbcDao.getInstance().insert(new Course("economy", "fingers"));
        CourseJdbcDao.getInstance().insert(new Course("algorithms", "candy"));
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
            StudentJdbcDao.getInstance().insert(new Student(names.get(rnd.nextInt(10)),
                    lastNames.get(rnd.nextInt(10))));
        }
    }

    private void assignStudentsToGroups() {
        for (var i = 1; i < 11; i++) {
            for (var a = 0; a < 10; a++) {
                long studentId = (long) ((Math.random() * (199)) + 1);
                if (StudentJdbcDao.getInstance().findById(studentId).getGroupId() == 0) {
                    StudentJdbcDao.getInstance().setGroup(studentId, (long) i);
                }
            }
        }
        for (var i = 0; i < 100; i++) {
            long studentId = (long) ((Math.random() * (199)) + 1);
            long groupId = (long) ((Math.random() * (9)) + 1);
            if (StudentJdbcDao.getInstance().findById(studentId).getGroupId() == 0) {
                StudentJdbcDao.getInstance().setGroup(studentId, groupId);
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
                CourseJdbcDao.getInstance().addStudentToTheCourse((long) i, courseId);
            }
        }
    }
}
