package com.foxminded.jdbc;

import com.foxminded.jdbc.application.ApplicationMenu;
import com.foxminded.jdbc.application.DatabaseConfiguration;
import com.foxminded.jdbc.application.UniversityApp;
import com.foxminded.jdbc.services.*;

public class Main {
    public static void main(String[] args) {
        DatabaseConfiguration configuration = new DatabaseConfiguration();
        AddStudentService addStudentService = new AddStudentService();
        AddStudentToTheCourseService addStudentToTheCourseService = new AddStudentToTheCourseService();
        DeleteStudentService deleteStudentService = new DeleteStudentService();
        FindGroupsWithStudentCountService findGroupsWithStudentCountService = new FindGroupsWithStudentCountService();
        FindStudentsRelatedToTheCourseService findStudentsRelatedToTheCourseService = new FindStudentsRelatedToTheCourseService();
        RemoveStudentFromCourseService removeStudentFromCourseService = new RemoveStudentFromCourseService();
        ApplicationMenu applicationMenu = new ApplicationMenu(addStudentService, addStudentToTheCourseService
                ,deleteStudentService,findGroupsWithStudentCountService, findStudentsRelatedToTheCourseService,
                removeStudentFromCourseService);
        UniversityApp universityApp = new UniversityApp(applicationMenu, configuration);
        universityApp.startApp();
    }
}
