package com.foxminded.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final String BANANABASE_PASSWORD_KEY = "db.password";
    private static final String BANANABASE_USERNAME_KEY = "db.username";
    private static final String BANANABASE_URL_KEY = "db.url";
    private static final String BANANASCHOOL_PASSWORD_KEY = "db.bananaschool.password";
    private static final String BANANASCHOOL_USERNAME_KEY = "db.bananaschool.username";
    private static final String BANANASCHOOL_URL_KEY = "db.bananaschool.url";
    private static final String BANANABASE_DB = "bananabase";
    private static final String BANANASCHOOL_DB = "bananaschool";
    private static final String POSTGRES_DRIVER = "org.postgresql.Driver";

    static {
        loadDriver();
    }

    private ConnectionManager() {
    }

    public static Connection open(String database) {
        try {
            if (database.equals(BANANABASE_DB)) {
                return DriverManager.getConnection(
                        PropertiesUtil.get(BANANABASE_URL_KEY),
                        PropertiesUtil.get(BANANABASE_USERNAME_KEY),
                        PropertiesUtil.get(BANANABASE_PASSWORD_KEY)
                );
            } else if (database.equals(BANANASCHOOL_DB)) {
                return DriverManager.getConnection(
                        PropertiesUtil.get(BANANASCHOOL_URL_KEY),
                        PropertiesUtil.get(BANANASCHOOL_USERNAME_KEY),
                        PropertiesUtil.get(BANANASCHOOL_PASSWORD_KEY)
                );
            } else {
                throw new ConnectionException();
            }
        } catch (SQLException e) {
            throw new ConnectionException(e);
        }
    }

    private static void loadDriver() {
        try {
            Class.forName(POSTGRES_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new ConnectionException(e);
        }
    }
}