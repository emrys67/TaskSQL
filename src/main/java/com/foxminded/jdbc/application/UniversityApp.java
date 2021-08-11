package com.foxminded.jdbc.application;


public class UniversityApp {
    private final ApplicationMenu menu;
    private final DatabaseConfiguration configuration;


    public UniversityApp(ApplicationMenu menu, DatabaseConfiguration configuration) {
        this.menu = menu;
        this.configuration = configuration;
    }

    public void startApp() {
        configuration.configure();
        menu.executeMenu();
    }
}
