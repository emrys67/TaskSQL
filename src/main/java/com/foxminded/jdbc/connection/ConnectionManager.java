package com.foxminded.jdbc.connection;

import com.foxminded.jdbc.exceptions.ConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final String BANANABASE_PASSWORD_KEY = "db.password";
    private static final String BANANABASE_USERNAME_KEY = "db.username";
    private static final String POSTGRES_DRIVER = "org.postgresql.Driver";

    static {
        loadDriver();
    }

    private ConnectionManager() {
    }

    public static Connection open(String url) {
        try {
            return DriverManager.getConnection(
                    url,
                    PropertiesUtil.get(BANANABASE_USERNAME_KEY),
                    PropertiesUtil.get(BANANABASE_PASSWORD_KEY)
            );
        } catch (SQLException e) {
            e.printStackTrace();
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