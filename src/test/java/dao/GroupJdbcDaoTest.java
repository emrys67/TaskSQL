package dao;

import com.foxminded.jdbc.connection.ConnectionManager;
import com.foxminded.jdbc.dao.GroupJdbcDao;
import com.foxminded.jdbc.entity.Group;
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

public class GroupJdbcDaoTest {
    public static final String JDBC_URL = "jdbc:h2:mem:default;DB_CLOSE_DELAY=-1;";
    private static final String TEST_DATABASE_URL = "jdbc:h2:mem:default;MODE=PostgreSQL;DB_CLOSE_DELAY=-1;";
    private static final String GROUP_NAME = "fg-12";
    private static final String JDBC_DRIVER = org.h2.Driver.class.getName();
    private static final String EMPTY = "";
    private static final String CONFIGURE_XML = "data.xml";
    private static final String CONFIGURE_SQL = "src\\test\\resources\\schema.sql";

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
    public static void setup() throws Exception {
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
    void findById() {
        String actual = GroupJdbcDao.getInstance(TEST_DATABASE_URL).findById((long) 1).getName();
        assertEquals(GROUP_NAME, actual);
    }

    @Test
    void findByWrongId() {
        Group actual = GroupJdbcDao.getInstance(TEST_DATABASE_URL).findById((long) 11);
        assertEquals(null, actual);
    }

    @Test
    void deleteById() {
        GroupJdbcDao.getInstance(TEST_DATABASE_URL).deleteById((long) 2);
        assertEquals(null, GroupJdbcDao.getInstance(TEST_DATABASE_URL).findById((long) 2));
    }

    @Test
    void findGroupsWithStudentCount() {
        int actual = GroupJdbcDao.getInstance(TEST_DATABASE_URL).findGroupsWithStudentCount(1).size();
        assertEquals(5, actual);
    }
}
