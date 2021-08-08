package dao;

import com.foxminded.jdbc.application.SqlScriptRunner;
import com.foxminded.jdbc.dao.CourseJdbcDao;
import com.foxminded.jdbc.entity.Course;
import com.foxminded.jdbc.exceptions.DaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class CourseJdbcDaoTest {
    private static final String EXCEPTION_SQL = "CourseDao sql exception";
    private static final String BANANASCHOOL_DB = "bananaschooltest";
    private static final String SQL_CONFIG_DDL = "src/test/resources/Ddl.sql";
    private static final String SQL_CONFIG_DML = "src/test/resources/sqlTwo.sql";
    private static final String MATH = "math";
    private static final String COURSE_JAPANESE = "japanese";
    private static final String COURSE_SUSHI = "sushi";
    SqlScriptRunner scriptRunner = new SqlScriptRunner();

    @BeforeEach
    void setUp() {
        scriptRunner.executeAllScripts(SQL_CONFIG_DDL, SQL_CONFIG_DML, BANANASCHOOL_DB);
    }

    @Test
    void studentAddedToTheCourse() {
        CourseJdbcDao.getInstance(BANANASCHOOL_DB).addStudentToTheCourse((long) 3, (long) 1);
        assertTrue(CourseJdbcDao.getInstance(BANANASCHOOL_DB).findCoursesRelatedToStudent((long) 3).stream().anyMatch(course -> course.getId() == 1));

    }

    @Test
    void addStudentToTheCourseWrongInput() {
        Exception exception = assertThrows(DaoException.class, () -> {
            CourseJdbcDao.getInstance(BANANASCHOOL_DB).addStudentToTheCourse((long) 15, (long) 13);
        });
        assertEquals(EXCEPTION_SQL, exception.getMessage());
    }

    @Test
    void studentRemovedFromTheCourse() {
        CourseJdbcDao.getInstance(BANANASCHOOL_DB).removeStudentFromCourse((long) 1, (long) 1);
        assertFalse(CourseJdbcDao.getInstance(BANANASCHOOL_DB).findCoursesRelatedToStudent((long) 1).stream().anyMatch(course -> course.getId() == 1));
    }

    @Test
    void removeStudentFromTheCourseWrongInput() {
        Exception exception = assertThrows(DaoException.class, () -> {
            CourseJdbcDao.getInstance(BANANASCHOOL_DB).addStudentToTheCourse((long) 154, (long) 153);
        });
        assertEquals(EXCEPTION_SQL, exception.getMessage());
    }

    @Test
    void CourseInserted() {
        CourseJdbcDao.getInstance(BANANASCHOOL_DB).insert(new Course(COURSE_JAPANESE, COURSE_SUSHI));
        assertTrue(CourseJdbcDao.getInstance(BANANASCHOOL_DB).getAllCourses().stream().anyMatch(course -> course.getName().equals(COURSE_JAPANESE)));
    }

    @Test
    void findById() {
        String actualName = CourseJdbcDao.getInstance(BANANASCHOOL_DB).findById((long) 1).getName();
        assertEquals(MATH, actualName);
    }

    @Test
    void findByWrongId() {
        Exception exception = assertThrows(DaoException.class, () -> {
            CourseJdbcDao.getInstance(BANANASCHOOL_DB).findById((long) 111);
        });
        assertEquals(EXCEPTION_SQL, exception.getMessage());
    }

    @Test
    void getAllCourses() {
        int actual = CourseJdbcDao.getInstance(BANANASCHOOL_DB).getAllCourses().size();
        assertEquals(5, actual);
    }

    @Test
    void findCoursesRelatedToStudent() {
        int actual = CourseJdbcDao.getInstance(BANANASCHOOL_DB).findCoursesRelatedToStudent((long) 1).size();
        assertEquals(0, actual);
    }

    @Test
    void findCoursesRelatedToWrongStudentId() {
        int actual = CourseJdbcDao.getInstance(BANANASCHOOL_DB).findCoursesRelatedToStudent((long) 111).size();
        assertEquals(0, actual);
    }
}
