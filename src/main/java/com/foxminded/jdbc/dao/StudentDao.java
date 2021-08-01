package com.foxminded.jdbc.dao;

import com.foxminded.jdbc.connection.ConnectionManager;
import com.foxminded.jdbc.entity.Student;

import java.sql.Connection;
import java.sql.SQLException;

public class StudentDao {
    private static final String BANANASCHOOL_DB = "bananaschool";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String LAST_NAME = "lastname";
    private static final String GROUP_ID = "group_id";
    private static final String EXCEPTION_SQL = "StudentDao sql exception";
    private static final StudentDao INSTANCE = new StudentDao();
    private static final String DELETE_BY_NAME = """
            DELETE FROM students
            WHERE id = ?""";
    private static final String DELETE_BY_ID = """
            DELETE FROM students
            WHERE name = ?""";
    private static final String SAVE = """
            INSERT INTO students (name, lastname)
            VALUES (?,?)""";
    private static final String FIND_BY_ID = """
            SELECT id, group_id, name, lastname
            FROM students
            WHERE id = ?
            """;
    private static final String FIND_BY_NAME = """
            SELECT id, group_id, name, lastname
            FROM students
            WHERE name = ?
            """;
    private static final String SET_GROUP = """
            UPDATE students
            SET group_id = ?
            WHERE id = ?
            """;

    private StudentDao() {
    }

    public static StudentDao getInstance() {
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

    public boolean deleteById(long id) {
        try (Connection connection = ConnectionManager.open(BANANASCHOOL_DB);
             var preparestatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparestatement.setLong(1, id);
            return preparestatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new RuntimeException(EXCEPTION_SQL);
        }
    }

    public void insert(String name, String lastname) {
        try (Connection connection = ConnectionManager.open(BANANASCHOOL_DB);
             var preparestatement = connection.prepareStatement(SAVE)) {
            preparestatement.setString(1, name);
            preparestatement.setString(2, lastname);
            preparestatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new RuntimeException(EXCEPTION_SQL);
        }
    }

    public Student findById(long id) {
        try (Connection connection = ConnectionManager.open(BANANASCHOOL_DB);
             var prepareStatement = connection.prepareStatement(FIND_BY_ID)) {
            prepareStatement.setLong(1, id);
            var resultSet = prepareStatement.executeQuery();
            Student student = null;
            if (resultSet.next()) {
                student = new Student(resultSet.getLong(ID), resultSet.getLong(GROUP_ID), resultSet.getString(NAME),
                        resultSet.getString(LAST_NAME));
            }
            return student;
        } catch (SQLException throwables) {
            throw new RuntimeException(EXCEPTION_SQL);
        }
    }

    public Student findByName(String name) {
        try (Connection connection = ConnectionManager.open(BANANASCHOOL_DB);
             var prepareStatement = connection.prepareStatement(FIND_BY_NAME)) {
            prepareStatement.setString(1, name);
            var resultSet = prepareStatement.executeQuery();
            Student student = null;
            if (resultSet.next()) {
                student = new Student(resultSet.getLong(ID), resultSet.getLong(GROUP_ID), resultSet.getString(NAME),
                        resultSet.getString(LAST_NAME));
            }
            return student;

        } catch (SQLException throwables) {
            throw new RuntimeException(EXCEPTION_SQL);
        }
    }

    public void setGroup(long student_id, long group_id) {
        try (Connection connection = ConnectionManager.open(BANANASCHOOL_DB);
             var prepareStatement = connection.prepareStatement(SET_GROUP)) {
            prepareStatement.setLong(1, group_id);
            prepareStatement.setLong(2, student_id);
            prepareStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new RuntimeException(EXCEPTION_SQL);
        }
    }
}

