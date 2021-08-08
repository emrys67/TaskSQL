package dao;

import com.foxminded.jdbc.application.SqlScriptRunner;
import com.foxminded.jdbc.dao.CourseJdbcDao;
import com.foxminded.jdbc.dao.StudentJdbcDao;
import com.foxminded.jdbc.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentJdbcDaoTest {
    private static final String BANANASCHOOL_DB = "bananaschooltest";
    private static final String SQL_CONFIG_DDL = "src/test/resources/Ddl.sql";
    private static final String SQL_CONFIG_DML = "src/test/resources/sqlTwo.sql";
    private static final String NAME = "name";
    private static final String LASTNAME = "lastname";
    private static final String MATH = "math";
    private static final String NAME_PETER = "Peter";
    SqlScriptRunner scriptRunner = new SqlScriptRunner();

    @BeforeEach
    void setUp() {
        scriptRunner.executeAllScripts(SQL_CONFIG_DDL, SQL_CONFIG_DML, BANANASCHOOL_DB);
    }

    @Test
    void findById() {
        String actual = StudentJdbcDao.getInstance(BANANASCHOOL_DB).findById((long) 1).getName();
        assertEquals(NAME_PETER, actual);
    }

    @Test
    void insert() {
        StudentJdbcDao.getInstance(BANANASCHOOL_DB).insert(new Student(NAME, LASTNAME));
        assertEquals(NAME, StudentJdbcDao.getInstance(BANANASCHOOL_DB).findById((long) 6).getName());
    }

    @Test
    void deleteById() {
        StudentJdbcDao.getInstance(BANANASCHOOL_DB).deleteById((long) 1);
        assertEquals(null, StudentJdbcDao.getInstance(BANANASCHOOL_DB).findById((long) 1));
    }

    @Test
    void findStudentsRelatedToCourse() {
        int actual = StudentJdbcDao.getInstance(BANANASCHOOL_DB).findStudentsRelatedToCourse(MATH).size();
        assertEquals(0, actual);
    }

    @Test
    void removeStudentFromCourse() {
        CourseJdbcDao.getInstance(BANANASCHOOL_DB).addStudentToTheCourse((long) 1, (long) 1);
        StudentJdbcDao.getInstance(BANANASCHOOL_DB).removeStudentFromCourse((long) 1, (long) 1);
        assertFalse(CourseJdbcDao.getInstance(BANANASCHOOL_DB).findCoursesRelatedToStudent((long) 1).stream().anyMatch(
                course -> course.getId() == 1));
    }
}
