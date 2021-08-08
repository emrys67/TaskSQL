package com.foxminded.jdbc;

import com.foxminded.jdbc.application.ApplicationMenu;
import com.foxminded.jdbc.application.DatabaseConfiguration;
import com.foxminded.jdbc.application.UniversityApp;
import com.foxminded.jdbc.services.*;

public class Main {
    public static void main(String[] args) {
        DatabaseConfiguration configuration = new DatabaseConfiguration();
        AddStudentService addStudentService = new AddStudentService();
        AddStudentToCourseService addStudentToCourseService = new AddStudentToCourseService();
        DeleteStudentService deleteStudentService = new DeleteStudentService();
        FindGroupsWithStudentCountService findGroupsWithStudentCountService = new FindGroupsWithStudentCountService();
        FindStudentsRelatedToCourseService findStudentsRelatedToCourseService = new FindStudentsRelatedToCourseService();
        RemoveStudentFromCourseService removeStudentFromCourseService = new RemoveStudentFromCourseService();
        ApplicationMenu applicationMenu = new ApplicationMenu(addStudentService,addStudentToCourseService
                ,deleteStudentService,findGroupsWithStudentCountService,findStudentsRelatedToCourseService,
                removeStudentFromCourseService);
        UniversityApp universityApp = new UniversityApp(applicationMenu, configuration);
        universityApp.startApp();
    }
}
