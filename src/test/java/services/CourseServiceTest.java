package services;

import com.foxminded.jdbc.dao.CourseJdbcDao;
import com.foxminded.jdbc.entity.Course;
import com.foxminded.jdbc.services.CourseService;
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
public class CourseServiceTest {
    ByteArrayInputStream in;
    InputStream sysInBackup = System.in;
    private CourseService courseService;
    private BufferedReader reader;
    @Mock
    private CourseJdbcDao courseDao;

    @BeforeEach
    void setUp() {
        courseService = new CourseService(courseDao);
    }

    @AfterEach
    void setIn() {
        System.setIn(sysInBackup);
    }

    @Test
    void addStudentToTheCourse() {
        in = new ByteArrayInputStream("0\n0".getBytes());
        System.setIn(in);
        reader = new BufferedReader(new InputStreamReader(System.in));
        when(courseDao.addStudentToTheCourse((long) 0, (long) 0)).thenReturn(true);
        courseService.addStudentToTheCourse(reader);
        verify(courseDao, times(1)).addStudentToTheCourse((long) 0, (long) 0);
    }

    @Test
    void findCoursesRelatedToStudent() {
        List<Course> list = new ArrayList<>();
        when(courseDao.findCoursesRelatedToStudent((long) 1)).thenReturn(list);
        courseService.findCoursesRelatedToStudent((long) 1);
        verify(courseDao, times(1)).findCoursesRelatedToStudent((long) 1);
    }

    @Test
    void removeStudentFromCourse() {
        in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        reader = new BufferedReader(new InputStreamReader(System.in));
        when(courseDao.removeStudentFromCourse((long) 1, (long) 1)).thenReturn(true);
        courseService.removeStudentFromTheCourse(reader, 1);
        verify(courseDao, times(1)).removeStudentFromCourse((long) 1, (long) 1);
    }

    @Test
    void getAllCourses() {
        List<Course> list = new ArrayList<>();
        in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        reader = new BufferedReader(new InputStreamReader(System.in));
        when(courseDao.getAllCourses()).thenReturn(list);
        courseService.getAllCourses();
        verify(courseDao, times(1)).getAllCourses();
    }

    @Test
    void createCourses(){
        when(courseDao.insert(any())).thenReturn(true);
        courseService.createCourses();
        verify(courseDao, times(10)).insert(any());
    }

    @Test
    void assignCourses(){
        when(courseDao.addStudentToTheCourse(any(),any())).thenReturn(true);
        courseService.assignCourses();
        verify(courseDao, atLeast(1)).addStudentToTheCourse(any(),any());
    }
}
