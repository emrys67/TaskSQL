package com.foxminded.jdbc.dao;

import com.foxminded.jdbc.connection.ConnectionManager;
import com.foxminded.jdbc.entity.Course;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseJdbcDao implements CourseDao<Course> {
    private static final String BANANASCHOOL_DB = "bananaschool";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String DISCRIPTION = "discription";
    private static final String EXCEPTION_SQL = "CourseDao sql exception";
    private static final CourseJdbcDao INSTANCE = new CourseJdbcDao();
    private static final String ADD_STUDENT_TO_THE_COURSE = """
            INSERT INTO bananaschool.public.students_courses (student_id, course_id)
            VALUES (?,?)
            """;
    private static final String DELETE_BY_ID = """
            DELETE FROM courses
            WHERE id = ?""";
    private static final String DELETE_STUDENT_FROM_THE_COURSE = """
            DELETE FROM bananaschool.public.students_courses
            WHERE student_id = ? AND course_id = ?""";
    private static final String INSERT = """
            INSERT INTO courses (name, discription)
            VALUES (?,?);""";
    private static final String FIND_BY_ID = """
            SELECT id, name, discription
            FROM courses
            WHERE id = ?
            """;
    private static final String GET_ALL_COURSES = """
            SELECT *
            FROM courses
            """;
    private static final String GET_STUDENT_COURSES = """
            SELECT courses.id, courses.name, courses.discription
            FROM courses
                     JOIN bananaschool.public.students_courses
                          ON course_id = courses.id
                     JOIN students
                          ON students.id = students_courses.student_id
            WHERE student_id = ?
            """;

    private CourseJdbcDao() {
    }

    public static CourseJdbcDao getInstance() {
        return INSTANCE;
    }

    public boolean addStudentToTheCourse(Long studentId, Long courseId) {
        try (Connection connection = ConnectionManager.open(BANANASCHOOL_DB);
             var preparestatement = connection.prepareStatement(ADD_STUDENT_TO_THE_COURSE)) {
            preparestatement.setLong(1, studentId);
            preparestatement.setLong(2, courseId);
            return preparestatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new DaoException(EXCEPTION_SQL);
        }
    }

    public boolean removeStudentFromCourse(Long studentId, Long courseId) {
        try (Connection connection = ConnectionManager.open(BANANASCHOOL_DB);
             var preparestatement = connection.prepareStatement(DELETE_STUDENT_FROM_THE_COURSE)) {
            preparestatement.setLong(1, studentId);
            preparestatement.setLong(2, courseId);
            return preparestatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new DaoException(EXCEPTION_SQL);
        }
    }

    public boolean deleteById(Long id) {
        try (Connection connection = ConnectionManager.open(BANANASCHOOL_DB);
             var preparestatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparestatement.setLong(1, id);
            return preparestatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new DaoException(EXCEPTION_SQL);
        }
    }

    @Override
    public boolean insert(Course course) {
        try (Connection connection = ConnectionManager.open(BANANASCHOOL_DB);
             var preparestatement = connection.prepareStatement(INSERT)) {
            preparestatement.setString(1, course.getName());
            preparestatement.setString(2, course.getDiscription());
            return preparestatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new DaoException(EXCEPTION_SQL);
        }
    }

    public Course findById(Long id) {
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
            throw new DaoException(EXCEPTION_SQL);
        }
    }

    public List<Course> getAllCourses() {
        try (Connection connection = ConnectionManager.open(BANANASCHOOL_DB);
             var prepareStatement = connection.prepareStatement(GET_ALL_COURSES)) {
            var resultSet = prepareStatement.executeQuery();
            List<Course> courses = new ArrayList<>();
            while (resultSet.next()) {
                courses.add(new Course(resultSet.getLong(ID), resultSet.getString(NAME), resultSet.getString(DISCRIPTION)));
            }
            return courses;
        } catch (SQLException throwables) {
            throw new DaoException(EXCEPTION_SQL);
        }
    }

    public List<Course> findCoursesRelatedToStudent(Long studentId) {
        try (Connection connection = ConnectionManager.open(BANANASCHOOL_DB);
             var prepareStatement = connection.prepareStatement(GET_STUDENT_COURSES)) {
            prepareStatement.setLong(1, studentId);
            var resultSet = prepareStatement.executeQuery();
            List<Course> courses = new ArrayList<>();
            while (resultSet.next()) {
                courses.add(new Course(resultSet.getLong(ID), resultSet.getString(NAME), resultSet.getString(DISCRIPTION)));
            }
            return courses;
        } catch (SQLException throwables) {
            throw new DaoException(EXCEPTION_SQL);
        }
    }
}
