package com.foxminded.jdbc;

import com.foxminded.jdbc.application.ApplicationMenu;
import com.foxminded.jdbc.application.DatabaseConfiguration;
import com.foxminded.jdbc.application.UniversityApp;
import com.foxminded.jdbc.services.*;

public class Main {
    public static void main(String[] args) {
        DatabaseConfiguration configuration = new DatabaseConfiguration();
        StudentJdbcService studentJdbcService = new StudentJdbcService();
        GroupJdbcService groupJdbcService = new GroupJdbcService();
        CourseJdbcService courseJdbcService = new CourseJdbcService();
        ApplicationMenu applicationMenu = new ApplicationMenu(studentJdbcService,courseJdbcService
                ,groupJdbcService);
        UniversityApp universityApp = new UniversityApp(applicationMenu, configuration);
        universityApp.startApp();
    }
}
