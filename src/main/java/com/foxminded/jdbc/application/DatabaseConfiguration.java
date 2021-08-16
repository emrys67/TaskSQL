package com.foxminded.jdbc.application;

import com.foxminded.jdbc.dao.CourseJdbcDao;
import com.foxminded.jdbc.dao.GroupJdbcDao;
import com.foxminded.jdbc.dao.StudentJdbcDao;
import com.foxminded.jdbc.entity.Course;
import com.foxminded.jdbc.entity.Group;
import com.foxminded.jdbc.entity.Student;
import com.foxminded.jdbc.readers.SqlScriptRunner;
import com.foxminded.jdbc.services.CourseService;
import com.foxminded.jdbc.services.GroupService;
import com.foxminded.jdbc.services.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DatabaseConfiguration {
    private static final String BANANASCHOOL_DB_URL = "jdbc:postgresql://localhost:5432/bananaschool";
    private static final String DDL_FILE_PATH = "src/main/resources/DDLfile.sql";
    private static final String DML_FILE_PATH = "src/main/resources/tablescreation.sql";
    private CourseService courseService;
    private GroupService groupService;
    private StudentService studentService;

    public DatabaseConfiguration(CourseService courseService, GroupService groupService, StudentService studentService) {
        this.courseService = courseService;
        this.groupService = groupService;
        this.studentService = studentService;
    }

    public void configure() {
        SqlScriptRunner scriptRunner = new SqlScriptRunner();
        scriptRunner.executeAllScripts(DDL_FILE_PATH, DML_FILE_PATH, BANANASCHOOL_DB_URL);
        fillInData();
    }

    private void fillInData() {
        groupService.createGroups();
        courseService.createCourses();
        studentService.createStudents();
        studentService.assignStudentsToGroups();
        courseService.assignCourses();
    }
}
