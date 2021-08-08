package com.foxminded.jdbc.application;

import com.foxminded.jdbc.connection.ConnectionManager;
import com.foxminded.jdbc.exceptions.UniversityAppException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class SqlScriptRunner {
    private static final String BANANABASE_DB = "bananabase";

    public void executeAllScripts(String ddlPath, String dmlPath, String database) {
        runScript(ddlPath, BANANABASE_DB);
        runScript(dmlPath, database);
    }

    private void runScript(String path, String database) {
        StringBuilder toRun = new StringBuilder();
        try (
                BufferedReader reader = new BufferedReader(new FileReader(path));
                Connection connectionOne = ConnectionManager.open(database)) {
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
