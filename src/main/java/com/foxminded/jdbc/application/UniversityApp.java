package com.foxminded.jdbc.application;


public class UniversityApp {
    private static final String WRONG_INPUT = "Wrong output";
    private static final String MENU = """
            Print char from a to f to choose
                a. Find all groups with less or equals student count                                                  
                b. Find all students related to course with given name                                               
                c. Add new student                                       
                d. Delete student by STUDENT_ID                   
                e. Add a student to the course (from a list)                                                  
                f. Remove the student from one of his or her courses""";
    private ApplicationMenu menu;
    private DatabaseConfiguration configuration;


    public UniversityApp(ApplicationMenu menu, DatabaseConfiguration configuration) {
        this.menu = menu;
        this.configuration = configuration;
    }

    public void startApp() {
            menu.executeMenu();
    }

    void processInput(String input) {
//        if (input.equals("a")) {
//            findGroupsWithStudentCount();
//        } else if (input.equals("b")) {
//            findStudentsRelatedToCourse();
//        } else if (input.equals("c")) {
//            addStudent();
//        } else if (input.equals("d")) {
//            deleteStudent();
//        } else if (input.equals("e")) {
//            addStudentToCourse();
//        } else if (input.equals("f")) {
//            removeStudentFromCourse();
//        } else if (!input.equals("g")) {
//            throw new UniversityAppException(WRONG_INPUT);
//        }
    }

//    public void findGroupsWithStudentCount() {
//        menu.findGroupsWithStudentCount();
//    }
//
//    public void findStudentsRelatedToCourse() {
//        menu.findStudentsRelatedToCourse();
//    }
//
//    public void addStudent() {
//        menu.addStudent();
//    }
//
//    public void deleteStudent() {
//        menu.deleteStudent();
//    }
//
//    public void addStudentToCourse() {
//        menu.addStudentToCourse();
//    }
//
//    public void removeStudentFromCourse() {
//        menu.removeStudentFromCourse();
//    }

//    public void setCommands(Menu menu) {
//        this.menu = menu;
//    }
//
//    public void setConfiguration(DatabaseConfiguration configuration) {
//        this.configuration = configuration;
//    }
}
