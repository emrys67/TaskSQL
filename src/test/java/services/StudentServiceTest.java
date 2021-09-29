package services;

import com.foxminded.jdbc.dao.StudentJdbcDao;
import com.foxminded.jdbc.entity.Student;
import com.foxminded.jdbc.services.StudentService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    ByteArrayInputStream in;
    InputStream sysInBackup = System.in;
    private StudentService studentService;
    private BufferedReader reader;
    @Mock
    private StudentJdbcDao studentDao;

    @BeforeEach
    void setUp() {
        studentService = new StudentService(studentDao);
    }

    @AfterEach
    void setIn() {
        System.setIn(sysInBackup);
    }

    @Test
    void addStudent() {
        Student student = new Student("name", "lastname");
        in = new ByteArrayInputStream("name lastname".getBytes());
        System.setIn(in);
        reader = new BufferedReader(new InputStreamReader(System.in));
        when(studentDao.insert(student)).thenReturn(true);
        studentService.addStudent(reader);
        verify(studentDao, times(1)).insert(student);
    }

    @Test
    void findStudentsRelatedToTheCourse() {
        List<Student> list = new ArrayList<>();
        in = new ByteArrayInputStream("math".getBytes());
        System.setIn(in);
        reader = new BufferedReader(new InputStreamReader(System.in));
        when(studentDao.findStudentsRelatedToCourse("math")).thenReturn(list);
        studentService.findStudentsRelatedToTheCourse(reader);
        verify(studentDao, times(1)).findStudentsRelatedToCourse("math");
    }

    @Test
    void deleteStudent() {
        in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        reader = new BufferedReader(new InputStreamReader(System.in));
        when(studentDao.deleteById((long) 1)).thenReturn(true);
        studentService.deleteStudent(reader);
        verify(studentDao, times(1)).deleteById((long) 1);
    }

    @Test
    void createStudents(){
        when(studentDao.insert(any())).thenReturn(true);
        studentService.createStudents();
        verify(studentDao,times(200)).insert(any());
    }
}
