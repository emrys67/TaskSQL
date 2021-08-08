package dao;

import com.foxminded.jdbc.application.SqlScriptRunner;
import com.foxminded.jdbc.dao.GroupJdbcDao;
import com.foxminded.jdbc.entity.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GroupJdbcDaoTest {
    private static final String BANANASCHOOL_DB = "bananaschooltest";
    private static final String SQL_CONFIG_DDL = "src/test/resources/Ddl.sql";
    private static final String SQL_CONFIG_DML = "src/test/resources/sqlTwo.sql";
    private static final String GROUP_NAME = "fg-12";
    SqlScriptRunner scriptRunner = new SqlScriptRunner();

    @BeforeEach
    void setUp() {
        scriptRunner.executeAllScripts(SQL_CONFIG_DDL, SQL_CONFIG_DML, BANANASCHOOL_DB);
    }

    @Test
    void findById() {
        String actual = GroupJdbcDao.getInstance(BANANASCHOOL_DB).findById((long) 1).getName();
        assertEquals(GROUP_NAME, actual);
    }

    @Test
    void findByWrongId() {
        Group actual = GroupJdbcDao.getInstance(BANANASCHOOL_DB).findById((long) 11);
        assertEquals(null, actual);
    }

    @Test
    void deleteById() {
        GroupJdbcDao.getInstance(BANANASCHOOL_DB).deleteById((long) 2);
        assertEquals(null, GroupJdbcDao.getInstance(BANANASCHOOL_DB).findById((long) 2));
    }

    @Test
    void findGroupsWithStudentCount() {
        int actual = GroupJdbcDao.getInstance(BANANASCHOOL_DB).findGroupsWithStudentCount(1).size();
        assertEquals(5, actual);
    }
}
