package services;

import com.foxminded.jdbc.dao.GroupJdbcDao;
import com.foxminded.jdbc.entity.Group;
import com.foxminded.jdbc.services.GroupService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GroupServiceTest {
    ByteArrayInputStream in;
    InputStream sysInBackup = System.in;
    private GroupService groupService;
    private BufferedReader reader;
    @Mock
    private GroupJdbcDao groupDao;

    @BeforeEach
    void setUp() {
        groupService = new GroupService(groupDao);
    }

    @AfterEach
    void setIn() {
        System.setIn(sysInBackup);
    }

    @Test
    void findGroupsWithStudentCount() {
        List<Group> list = new ArrayList<>();
        in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        reader = new BufferedReader(new InputStreamReader(System.in));
        when(groupDao.findGroupsWithStudentCount(1)).thenReturn(list);
        groupService.findGroupsWithStudentCount(reader);
        verify(groupDao, times(1)).findGroupsWithStudentCount(1);
    }

    @Test
    void createGroups(){
        when(groupDao.insert(any())).thenReturn(true);
        groupService.createGroups();
        verify(groupDao, times(10)).insert(any());
    }
}
