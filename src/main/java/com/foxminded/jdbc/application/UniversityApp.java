package com.foxminded.jdbc.application;


public class UniversityApp {
    private ApplicationMenu menu;
    private DatabaseConfiguration configuration;


    public UniversityApp(ApplicationMenu menu, DatabaseConfiguration configuration) {
        this.menu = menu;
        this.configuration = configuration;
    }

    public void startApp() {
            configuration.configure();
            menu.executeMenu();
    }
}
