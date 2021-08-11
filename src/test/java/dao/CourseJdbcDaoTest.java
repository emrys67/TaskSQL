package dao;

import com.foxminded.jdbc.connection.ConnectionManager;
import com.foxminded.jdbc.dao.StudentJdbcDao;
import com.foxminded.jdbc.dao.CourseJdbcDao;
import com.foxminded.jdbc.entity.Course;
import com.foxminded.jdbc.exceptions.DaoException;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class CourseJdbcDaoTest {
    public static final String JDBC_URL = "jdbc:h2:mem:default;DB_CLOSE_DELAY=-1;";
    private static final String EXCEPTION_SQL = "CourseDao sql exception";
    private static final String CONFIGURE_XML = "data.xml";
    private static final String CONFIGURE_SQL = "src\\test\\resources\\schema.sql";
    private static final String TEST_DATABASE_URL = "jdbc:h2:mem:default;MODE=PostgreSQL;DB_CLOSE_DELAY=-1;";
    private static final String MATH = "math";
    private static final String COURSE_JAPANESE = "japanese";
    private static final String COURSE_SUSHI = "sushi";
    private static final String JDBC_DRIVER = org.h2.Driver.class.getName();
    private static final String EMPTY = "";
    private static IDatabaseTester tester = null;

    private static IDatabaseTester initDatabaseTester() throws Exception {
        JdbcDatabaseTester tester = new JdbcDatabaseTester(JDBC_DRIVER, JDBC_URL, EMPTY, EMPTY);
        tester.setDataSet(initDataSet());
        tester.setSetUpOperation(DatabaseOperation.REFRESH);
        tester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
        return tester;
    }

    private static IDataSet initDataSet() throws Exception {
        try (InputStream is = CourseJdbcDaoTest.class.getClassLoader().getResourceAsStream(CONFIGURE_XML)) {
            return new FlatXmlDataSetBuilder().build(is);
        }
    }

    @BeforeAll
    static void setup() throws Exception {
        File file = new File(CONFIGURE_SQL);
        RunScript.execute(ConnectionManager.open(TEST_DATABASE_URL), new FileReader(file));
    }

    @BeforeEach
    void bef() throws Exception {
        tester = initDatabaseTester();
        tester.onSetup();
    }

    @AfterEach
    public void tearDown() throws Exception {
        tester.onTearDown();
    }

    @Test
    void studentAddedToTheCourse() {
        CourseJdbcDao.getInstance(TEST_DATABASE_URL).addStudentToTheCourse((long) 3, (long) 1);
        assertTrue(CourseJdbcDao.getInstance(TEST_DATABASE_URL).findCoursesRelatedToStudent((long) 3).stream().anyMatch(course -> course.getId() == 1));

    }

    @Test
    void addStudentToTheCourseWrongInput() {
        Exception exception = assertThrows(DaoException.class, () -> {
            CourseJdbcDao.getInstance(TEST_DATABASE_URL).addStudentToTheCourse((long) 15, (long) 13);
        });
        assertEquals(EXCEPTION_SQL, exception.getMessage());
    }

    @Test
    void studentRemovedFromTheCourse() {
        CourseJdbcDao.getInstance(TEST_DATABASE_URL).removeStudentFromCourse((long) 1, (long) 1);
        assertFalse(CourseJdbcDao.getInstance(TEST_DATABASE_URL).findCoursesRelatedToStudent((long) 1).stream().anyMatch(course -> course.getId() == 1));
    }

    @Test
    void removeStudentFromTheCourseWrongInput() {
        Exception exception = assertThrows(DaoException.class, () -> {
            CourseJdbcDao.getInstance(TEST_DATABASE_URL).addStudentToTheCourse((long) 154, (long) 153);
        });
        assertEquals(EXCEPTION_SQL, exception.getMessage());
    }

    @Test
    void CourseInserted() {
        CourseJdbcDao.getInstance(TEST_DATABASE_URL).insert(new Course(COURSE_JAPANESE, COURSE_SUSHI));
        assertTrue(CourseJdbcDao.getInstance(TEST_DATABASE_URL).getAllCourses().stream().anyMatch(course -> course.getName().equals(COURSE_JAPANESE)));
    }

    @Test
    void findById() {
        String actualName = CourseJdbcDao.getInstance(TEST_DATABASE_URL).findById((long) 1).getName();
        assertEquals(MATH, actualName);
    }

    @Test
    void findByWrongId() {
        Exception exception = assertThrows(DaoException.class, () -> {
            CourseJdbcDao.getInstance(TEST_DATABASE_URL).findById((long) 111);
        });
        assertEquals(EXCEPTION_SQL, exception.getMessage());
    }

    @Test
    void getAllCourses() {
        int actual = CourseJdbcDao.getInstance(TEST_DATABASE_URL).getAllCourses().size();
        assertEquals(5, actual);
    }

    @Test
    void findCoursesRelatedToStudent() {
        int actual = CourseJdbcDao.getInstance(TEST_DATABASE_URL).findCoursesRelatedToStudent((long) 1).size();
        assertEquals(0, actual);
    }

    @Test
    void findCoursesRelatedToWrongStudentId() {
        int actual = CourseJdbcDao.getInstance(TEST_DATABASE_URL).findCoursesRelatedToStudent((long) 111).size();
        assertEquals(0, actual);
    }

}
