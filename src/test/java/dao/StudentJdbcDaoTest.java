package dao;

import com.foxminded.jdbc.connection.ConnectionManager;
import com.foxminded.jdbc.dao.CourseJdbcDao;
import com.foxminded.jdbc.dao.StudentJdbcDao;
import com.foxminded.jdbc.entity.Student;
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

public class StudentJdbcDaoTest {
    public static final String JDBC_URL = "jdbc:h2:mem:default;DB_CLOSE_DELAY=-1;";
    private static final String TEST_DATABASE_URL = "jdbc:h2:mem:default;MODE=PostgreSQL;DB_CLOSE_DELAY=-1;";
    private static final String NAME = "name";
    private static final String LASTNAME = "lastname";
    private static final String MATH = "math";
    private static final String NAME_PETER = "Peter";
    private static final String JDBC_DRIVER = org.h2.Driver.class.getName();
    private static final String EMPTY = "";
    private static final String CONFIGURE_XML = "data.xml";
    private static final String CONFIGURE_SQL = "src\\test\\resources\\schema.sql";
    private static IDatabaseTester tester = null;
    private StudentJdbcDao studentJdbcDao;
    private CourseJdbcDao courseJdbcDao;

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
    public static void setup() throws Exception {
        File file = new File(CONFIGURE_SQL);
        RunScript.execute(ConnectionManager.open(TEST_DATABASE_URL), new FileReader(file));
    }

    @BeforeEach
    void bef() throws Exception {
        tester = initDatabaseTester();
        tester.onSetup();
        studentJdbcDao = new StudentJdbcDao(TEST_DATABASE_URL);
        courseJdbcDao = new CourseJdbcDao(TEST_DATABASE_URL);
    }

    @AfterEach
    public void tearDown() throws Exception {
        tester.onTearDown();
    }

    @Test
    void findById() {
        String actual = studentJdbcDao.findById((long) 1).getName();
        assertEquals(NAME_PETER, actual);
    }

    @Test
    void insert() {
        studentJdbcDao.insert(new Student(NAME, LASTNAME));
        assertEquals(NAME, studentJdbcDao.findById((long) 6).getName());
    }

    @Test
    void deleteById() {
        studentJdbcDao.deleteById((long) 1);
        assertEquals(null, studentJdbcDao.findById((long) 1));
    }

    @Test
    void findStudentsRelatedToCourse() {
        int actual = studentJdbcDao.findStudentsRelatedToCourse(MATH).size();
        assertEquals(0, actual);
    }

    @Test
    void removeStudentFromCourse() {
        courseJdbcDao.addStudentToTheCourse((long) 1, (long) 1);
        studentJdbcDao.removeStudentFromCourse((long) 1, (long) 1);
        assertFalse(courseJdbcDao.findCoursesRelatedToStudent((long) 1).stream().anyMatch(
                course -> course.getId() == 1));
    }
}
