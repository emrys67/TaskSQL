package com.foxminded.jdbc.dao;

import com.foxminded.jdbc.connection.ConnectionManager;
import com.foxminded.jdbc.entity.Student;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentJdbcDao implements StudentDao<Student> {
    private static final String BANANASCHOOL_DB = "bananaschool";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String LAST_NAME = "lastname";
    private static final String GROUP_ID = "group_id";
    private static final String EXCEPTION_SQL = "StudentDao sql exception";
    private static final StudentJdbcDao INSTANCE = new StudentJdbcDao();
    private static final String DELETE_BY_ID = """
            DELETE FROM students
            WHERE students.id = ?""";
    private static final String DELETE_STUDENT_FROM_COURSE = """
            DELETE FROM bananaschool.public.students_courses
            WHERE course_id = ? AND student_id = ?""";
    private static final String INSERT = """
            INSERT INTO students (name, lastname)
            VALUES (?,?)""";
    private static final String FIND_BY_ID = """
            SELECT id, group_id, name, lastname
            FROM students
            WHERE id = ?
            """;
    private static final String SET_GROUP = """
            UPDATE students
            SET group_id = ?
            WHERE id = ?
            """;
    private static final String FIND_STUDENTS_RELATED_TO_COURSE = """
            SELECT students.id, students.name, students.lastname, students.group_id
                               FROM students
                                        JOIN bananaschool.public.students_courses
                                             ON students.id = students_courses.student_id
                                        JOIN courses
                                             ON courses.id = students_courses.course_id
                               WHERE courses.name = ?
                               
            """;

    private StudentJdbcDao() {
    }

    public static StudentJdbcDao getInstance() {
        return INSTANCE;
    }

    public boolean removeStudentFromCourse(Long student, Long course) {
        try (Connection connection = ConnectionManager.open(BANANASCHOOL_DB);
             var preparestatement = connection.prepareStatement(DELETE_STUDENT_FROM_COURSE)) {
            preparestatement.setLong(1, course);
            preparestatement.setLong(1, student);
            return preparestatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new DaoException(EXCEPTION_SQL);
        }
    }

    public List<Student> findStudentsRelatedToCourse(String courseName) {
        try (Connection connection = ConnectionManager.open(BANANASCHOOL_DB);
             var prepareStatement = connection.prepareStatement(FIND_STUDENTS_RELATED_TO_COURSE)) {
            prepareStatement.setString(1, courseName);
            var resultSet = prepareStatement.executeQuery();
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                students.add(new Student(resultSet.getLong(ID), resultSet.getLong(GROUP_ID), resultSet.getString(NAME), resultSet.getString(LAST_NAME)));
            }
            return students;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DaoException(EXCEPTION_SQL);
        }
    }


    public boolean deleteById(Long id) {
        try (Connection connection = ConnectionManager.open(BANANASCHOOL_DB);
             var preparestatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparestatement.setLong(1, id);
            return preparestatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DaoException(EXCEPTION_SQL);
        }
    }

    public boolean insert(Student student) {
        try (Connection connection = ConnectionManager.open(BANANASCHOOL_DB);
             var preparestatement = connection.prepareStatement(INSERT)) {
            preparestatement.setString(1, student.getName());
            preparestatement.setString(2, student.getLastname());
            return preparestatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new DaoException(EXCEPTION_SQL);
        }
    }

    public Student findById(Long id) {
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
            throw new DaoException(EXCEPTION_SQL);
        }
    }


    public boolean setGroup(Long studentId, Long groupId) {
        try (Connection connection = ConnectionManager.open(BANANASCHOOL_DB);
             var prepareStatement = connection.prepareStatement(SET_GROUP)) {
            prepareStatement.setLong(1, groupId);
            prepareStatement.setLong(2, studentId);
            return prepareStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new DaoException(EXCEPTION_SQL);
        }
    }
}

