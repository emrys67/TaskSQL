package com.foxminded.jdbc.readers;

import com.foxminded.jdbc.connection.ConnectionManager;
import com.foxminded.jdbc.exceptions.UniversityAppException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class SqlScriptRunner {
    private static final String BANANABASE_DB_URL = "jdbc:postgresql://localhost:5432/bananabase";

    public void executeAllScripts(String ddlPath, String dmlPath, String databaseURL) {
        runScript(ddlPath, BANANABASE_DB_URL);
        runScript(dmlPath, databaseURL);
    }

    private void runScript(String path, String databaseURL) {
        StringBuilder toRun = new StringBuilder();
        try (
                BufferedReader reader = new BufferedReader(new FileReader(path));
                Connection connectionOne = ConnectionManager.open(databaseURL)) {
            while (reader.ready()) {
                toRun.append(reader.readLine());
            }
            var statement = connectionOne.createStatement();
            statement.execute(toRun.toString());
            statement.close();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
            throw new UniversityAppException();
        }
    }
}
