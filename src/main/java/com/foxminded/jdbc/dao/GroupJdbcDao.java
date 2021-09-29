package com.foxminded.jdbc.dao;

import com.foxminded.jdbc.connection.ConnectionManager;
import com.foxminded.jdbc.entity.Group;
import com.foxminded.jdbc.entity.Student;
import com.foxminded.jdbc.exceptions.DaoException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupJdbcDao implements GroupDao<Group> {
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String EXCEPTION_SQL = "GroupDao sql exception";
    private static final String DELETE_BY_ID = """
            DELETE FROM groups
            WHERE id = ?""";
    private static final String INSERT = """
            INSERT INTO groups (name)
            VALUES (?)""";
    private static final String FIND_BY_ID = """
            SELECT id, name
            FROM groups
            WHERE id = ?
            """;
    private static final String FIND_BY_STUDENT_COUNT = """
            SELECT
            groups.id, groups.name
            FROM groups
            JOIN students
            ON groups.id = students.group_id
            GROUP BY groups.id
            HAVING count(students.id) <= ?
            """;
    private final String currentDataBaseURL;

    public GroupJdbcDao(String currentDataBaseURL) {
        this.currentDataBaseURL = currentDataBaseURL;
    }

    public List<Group> findGroupsWithStudentCount(int count) {
        try (Connection connection = ConnectionManager.open(currentDataBaseURL);
             var prepareStatement = connection.prepareStatement(FIND_BY_STUDENT_COUNT)) {
            prepareStatement.setLong(1, count);
            var resultSet = prepareStatement.executeQuery();
            List<Group> group = new ArrayList<>();
            while (resultSet.next()) {
                group.add(new Group(resultSet.getLong(ID), resultSet.getString(NAME)));
            }
            return group;

        } catch (SQLException throwables) {
            throw new DaoException(EXCEPTION_SQL);
        }
    }

    public boolean deleteById(Long id) {
        try (Connection connection = ConnectionManager.open(currentDataBaseURL);
             var preparestatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparestatement.setLong(1, id);
            return preparestatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DaoException(EXCEPTION_SQL);
        }
    }

    public boolean insert(Group group) {
        try (Connection connection = ConnectionManager.open(currentDataBaseURL);
             var preparestatement = connection.prepareStatement(INSERT)) {
            preparestatement.setString(1, group.getName());
            return preparestatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new DaoException(EXCEPTION_SQL);
        }
    }

    public Group findById(Long id) {
        try (Connection connection = ConnectionManager.open(currentDataBaseURL);
             var prepareStatement = connection.prepareStatement(FIND_BY_ID)) {
            prepareStatement.setLong(1, id);
            var resultSet = prepareStatement.executeQuery();
            Group group = null;
            if (resultSet.next()) {
                group = new Group(resultSet.getLong(ID), resultSet.getString(NAME));
            }
            return group;

        } catch (SQLException throwables) {
            throw new DaoException(EXCEPTION_SQL);
        }
    }
}
