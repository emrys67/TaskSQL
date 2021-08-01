package com.foxminded.jdbc.dao;

import com.foxminded.jdbc.connection.ConnectionManager;
import com.foxminded.jdbc.entity.Course;

import java.sql.Connection;
import java.sql.SQLException;

public class CourseDao {
    private static final String BANANASCHOOL_DB = "bananaschool";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String DISCRIPTION = "discription";
    private static final String EXCEPTION_SQL = "CourseDao sql exception";
    private static final CourseDao INSTANCE = new CourseDao();
    private static final String DELETE = """
            DELETE FROM courses
            WHERE id = ?""";
    private static final String SAVE = """
            INSERT INTO courses (name, discription)
            VALUES (?,?);""";
    private static final String FIND_BY_ID = """
            SELECT id, name, discription
            FROM courses
            WHERE id = ?
            """;
    private static final String FIND_BY_NAME = """
            SELECT id, name, discription
            FROM courses
            WHERE name = ?
            """;

    private CourseDao() {
    }

    public static CourseDao getInstance() {
        return INSTANCE;
    }

    public boolean delete(Long id) {
        try (Connection connection = ConnectionManager.open(BANANASCHOOL_DB);
             var preparestatement = connection.prepareStatement(DELETE)) {
            preparestatement.setLong(1, id);
            return preparestatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new RuntimeException(EXCEPTION_SQL);
        }
    }

    public void insert(String name, String discription) {
        try (Connection connection = ConnectionManager.open(BANANASCHOOL_DB);
             var preparestatement = connection.prepareStatement(SAVE)) {
            preparestatement.setString(1, name);
            preparestatement.setString(2, discription);
            preparestatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new RuntimeException(EXCEPTION_SQL);
        }
    }

    public Course findById(long id) {
        try (Connection connection = ConnectionManager.open(BANANASCHOOL_DB);
             var prepareStatement = connection.prepareStatement(FIND_BY_ID)) {
            prepareStatement.setLong(1, id);
            var resultSet = prepareStatement.executeQuery();
            Course course = null;
            if (resultSet.next()) {
                course = new Course(resultSet.getLong(ID), resultSet.getString(NAME),
                        resultSet.getString(DISCRIPTION));
            }
            return course;

        } catch (SQLException throwables) {
            throw new RuntimeException(EXCEPTION_SQL);
        }
    }

    public Course findByName(String name) {
        try (Connection connection = ConnectionManager.open(BANANASCHOOL_DB);
             var prepareStatement = connection.prepareStatement(FIND_BY_NAME)) {
            prepareStatement.setString(1, name);
            var resultSet = prepareStatement.executeQuery();
            Course course = null;
            if (resultSet.next()) {
                course = new Course(resultSet.getLong(ID), resultSet.getString(NAME),
                        resultSet.getString(DISCRIPTION));
            }
            return course;

        } catch (SQLException throwables) {
            throw new RuntimeException(EXCEPTION_SQL);
        }
    }
}
