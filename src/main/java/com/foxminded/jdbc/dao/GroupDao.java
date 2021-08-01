package com.foxminded.jdbc.dao;

import com.foxminded.jdbc.connection.ConnectionManager;
import com.foxminded.jdbc.entity.Group;

import java.sql.Connection;
import java.sql.SQLException;

public class GroupDao {
    private static final String BANANASCHOOL_DB = "bananaschool";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String EXCEPTION_SQL = "GroupDao sql exception";
    private static final GroupDao INSTANCE = new GroupDao();
    private static final String DELETE_BY_NAME = """
            DELETE FROM bananabase.school.groups
            WHERE id = ?""";
    private static final String DELETE_BY_ID = """
            DELETE FROM groups
            WHERE name = ?""";
    private static final String SAVE = """
            INSERT INTO groups (name)
            VALUES (?)""";
    private static final String FIND_BY_ID = """
            SELECT id, name
            FROM bananabase.school.groups
            WHERE id = ?
            """;
    private static final String FIND_BY_NAME = """
            SELECT id, name
            FROM bananabase.school.groups
            WHERE name = ?
            """;

    private GroupDao() {
    }

    public static GroupDao getInstance() {
        return INSTANCE;
    }

    public boolean deleteByName(String name) {
        try (Connection connection = ConnectionManager.open(BANANASCHOOL_DB);
             var preparestatement = connection.prepareStatement(DELETE_BY_NAME)) {
            preparestatement.setString(1, name);
            return preparestatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new RuntimeException(EXCEPTION_SQL);
        }
    }

    public boolean deleteById(Long id) {
        try (Connection connection = ConnectionManager.open(BANANASCHOOL_DB);
             var preparestatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparestatement.setLong(1, id);
            return preparestatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new RuntimeException(EXCEPTION_SQL);
        }
    }

    public boolean insert(String name) {
        try (Connection connection = ConnectionManager.open(BANANASCHOOL_DB);
             var preparestatement = connection.prepareStatement(SAVE)) {
            preparestatement.setString(1, name);
            return preparestatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new RuntimeException(EXCEPTION_SQL);
        }
    }

    public Group findById(long id) {
        try (Connection connection = ConnectionManager.open(BANANASCHOOL_DB);
             var prepareStatement = connection.prepareStatement(FIND_BY_ID)) {
            prepareStatement.setLong(1, id);
            var resultSet = prepareStatement.executeQuery();
            Group group = null;
            if (resultSet.next()) {
                group = new Group(resultSet.getLong(ID), resultSet.getString(NAME));
            }
            return group;

        } catch (SQLException throwables) {
            throw new RuntimeException(EXCEPTION_SQL);
        }
    }

    public Group findByName(String name) {
        try (Connection connection = ConnectionManager.open(BANANASCHOOL_DB);
             var prepareStatement = connection.prepareStatement(FIND_BY_NAME)) {
            prepareStatement.setString(1, name);
            var resultSet = prepareStatement.executeQuery();
            Group group = null;
            if (resultSet.next()) {
                group = new Group(resultSet.getLong(ID), resultSet.getString(NAME));
            }
            return group;

        } catch (SQLException throwables) {
            throw new RuntimeException(EXCEPTION_SQL);
        }
    }
}
