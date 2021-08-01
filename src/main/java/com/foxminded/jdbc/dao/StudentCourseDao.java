package com.foxminded.jdbc.dao;

import com.foxminded.jdbc.connection.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;

public class StudentCourseDao {
    private static final String BANANASCHOOL_DB = "bananaschool";
    private static final String EXCEPTION_SQL = "StudentCourseDao sql exception";
    private static final StudentCourseDao INSTANCE = new StudentCourseDao();
    private static final String SAVE = """
            INSERT INTO bananaschool.public.students_courses (student_id, course_id)
            VALUES (?,?)""";
    private static final String DELETE_BY_STUDENT_ID = """
            DELETE FROM bananaschool.public.students_courses
            WHERE student_id = ?""";
    private static final String FIND_BY_COURSE_ID = """
            SELECT student_id, course_id
            FROM bananaschool.public.students_courses
            WHERE course_id = ?
            """;

    private StudentCourseDao() {
    }

    public static StudentCourseDao getInstance() {
        return INSTANCE;
    }

    public void deleteByStudentId(long id) {
        try (Connection connection = ConnectionManager.open(BANANASCHOOL_DB);
             var preparestatement = connection.prepareStatement(DELETE_BY_STUDENT_ID)) {
            preparestatement.setLong(1, id);
            preparestatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new RuntimeException(EXCEPTION_SQL);
        }
    }

    public void insert(long student_id, long course_id) {
        try (Connection connection = ConnectionManager.open(BANANASCHOOL_DB);
             var preparestatement = connection.prepareStatement(SAVE)) {
            preparestatement.setLong(1, student_id);
            preparestatement.setLong(2, course_id);
            preparestatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new RuntimeException(EXCEPTION_SQL);
        }
    }
}
