package com.foxminded.jdbc;

import com.foxminded.jdbc.application.ApplicationMenu;
import com.foxminded.jdbc.application.DatabaseConfiguration;
import com.foxminded.jdbc.application.UniversityApp;
import com.foxminded.jdbc.dao.CourseJdbcDao;
import com.foxminded.jdbc.dao.GroupJdbcDao;
import com.foxminded.jdbc.dao.StudentJdbcDao;
import com.foxminded.jdbc.services.*;

public class Main {
    public static void main(String[] args) {
        StudentJdbcDao studentJdbcDao = new StudentJdbcDao("jdbc:postgresql://localhost:5432/bananaschool");
        CourseJdbcDao courseJdbcDao = new CourseJdbcDao("jdbc:postgresql://localhost:5432/bananaschool");
        GroupJdbcDao groupJdbcDao = new GroupJdbcDao("jdbc:postgresql://localhost:5432/bananaschool");
        StudentService studentService = new StudentService(studentJdbcDao);
        GroupService groupService = new GroupService(groupJdbcDao);
        CourseService courseService = new CourseService(courseJdbcDao);
        DatabaseConfiguration configuration = new DatabaseConfiguration(courseService,groupService,studentService);
        ApplicationMenu applicationMenu = new ApplicationMenu(studentService, courseService
                , groupService);
        UniversityApp universityApp = new UniversityApp(applicationMenu, configuration);
        universityApp.startApp();
    }
}
