package com.foxminded.jdbc;

import com.foxminded.jdbc.app.CommandsApp;
import com.foxminded.jdbc.app.DatabaseConfiguration;
import com.foxminded.jdbc.app.UniversityApp;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        DatabaseConfiguration configuration = new DatabaseConfiguration();
        CommandsApp commandsApp = new CommandsApp();
        UniversityApp universityApp = new UniversityApp(commandsApp, configuration);
        universityApp.startApp();
    }
}
