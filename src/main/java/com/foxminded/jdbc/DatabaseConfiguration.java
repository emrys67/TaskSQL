package com.foxminded.jdbc;

import com.foxminded.jdbc.connection.ConnectionManager;
import com.foxminded.jdbc.dao.CourseDao;
import com.foxminded.jdbc.dao.GroupDao;
import com.foxminded.jdbc.dao.StudentCourseDao;
import com.foxminded.jdbc.dao.StudentDao;

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
        String toRun = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(DDL_FILE_PATH));
             Connection connectionOne = ConnectionManager.open(BANANABASE_DB)) {
            while (reader.ready()) {
                toRun += reader.readLine();
            }
            var statement = connectionOne.createStatement();
            statement.execute(toRun);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(EXCEPTION_SQL);
        } catch (IOException e) {
            throw new RuntimeException(EXCEPTION_SQL);
        } catch (SQLException throwables) {
            throw new RuntimeException(EXCEPTION_SQL);
        }
    }

    private void runDmlSript() {
        String toRun = "";
        try (BufferedReader secondReader = new BufferedReader(new FileReader(DML_FILE_PATH));
             var connection = ConnectionManager.open(BANANASCHOOL_DB)) {
            while (secondReader.ready()) {
                toRun += secondReader.readLine();
            }
            var statement = connection.createStatement();
            statement.execute(toRun);
        } catch (SQLException throwables) {
            throw new RuntimeException(EXCEPTION_SQL);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(EXCEPTION_SQL);
        } catch (IOException e) {
            throw new RuntimeException(EXCEPTION_SQL);
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
            GroupDao.getInstance().insert(name);
        }
    }

    private void createCourses() {
        CourseDao.getInstance().insert("math", "onion");
        CourseDao.getInstance().insert("oop", "fish");
        CourseDao.getInstance().insert("biology", "potatto");
        CourseDao.getInstance().insert("history", "spagetti");
        CourseDao.getInstance().insert("english", "nudles");
        CourseDao.getInstance().insert("spanish", "kebab");
        CourseDao.getInstance().insert("russian", "pizza");
        CourseDao.getInstance().insert("sleeping", "bananafish");
        CourseDao.getInstance().insert("economy", "fingers");
        CourseDao.getInstance().insert("algorithms", "candy");
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
            StudentDao.getInstance().insert(names.get(rnd.nextInt((9 - 0) + 1) + 0),
                    lastNames.get(rnd.nextInt((9 - 0) + 1) + 0));
        }
    }

    private void assignStudentsToGroups() {
        Random rnd = new Random();
        for (int i = 1; i < 11; i++) {
            for (int a = 0; a < 10; a++) {
                long student_id = (long) ((Math.random() * (199)) + 1);
                if (StudentDao.getInstance().findById(student_id).getGroup_id() == 0) {
                    StudentDao.getInstance().setGroup(student_id, i);
                } else {
                    if (a != 0) {
                        a--;
                    }
                }
            }
        }
        for (int i = 0; i < 100; i++) {
            long student_id = (long) ((Math.random() * (199)) + 1);
            long group_id = (long) ((Math.random() * (9)) + 1);
            if (StudentDao.getInstance().findById(student_id).getGroup_id() == 0) {
                StudentDao.getInstance().setGroup(student_id, group_id);
            }
        }
    }

    private void assignCourses() {
        long course_id;
        int repeats;
        for (int i = 1; i < 201; i++) {
            repeats = (int) ((Math.random() * (2)) + 1);
            for (int a = 0; a < repeats; a++) {
                course_id = (long) ((Math.random() * (9)) + 1);
                StudentCourseDao.getInstance().insert(i, course_id);
            }
        }
    }
}
