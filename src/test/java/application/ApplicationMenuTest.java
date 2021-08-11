package application;

import com.foxminded.jdbc.application.ApplicationMenu;
import com.foxminded.jdbc.services.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ApplicationMenuTest {
    private ApplicationMenu applicationMenu;
    private BufferedReader reader;
    @Mock
    private StudentJdbcService studentJdbcService;
    @Mock
    private GroupJdbcService groupJdbcService;
    @Mock
    private CourseJdbcService courseJdbcService;

    @BeforeEach
    void setUp() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        applicationMenu = new ApplicationMenu(studentJdbcService,courseJdbcService,groupJdbcService);
    }

    @Test
    void addStudentServicesWasUsed() {
        doNothing().when(studentJdbcService).addStudent(reader);
        applicationMenu.addStudent(reader);
        verify(studentJdbcService, times(1)).addStudent(reader);
    }

    @Test
    void addStudentToCourseServicesWasUsed() {
        doNothing().when(courseJdbcService).addStudentToTheCourse(reader);
        applicationMenu.addStudentToCourse(reader);
        verify(courseJdbcService, times(1)).addStudentToTheCourse(reader);
    }

    @Test
    void deleteStudentServicesWasUsed() {
        doNothing().when(studentJdbcService).deleteStudent(reader);
        applicationMenu.deleteStudent(reader);
        verify(studentJdbcService, times(1)).deleteStudent(reader);
    }

    @Test
    void findGroupsWithStudentServicesWasUsed() {
        doNothing().when(groupJdbcService).findGroupsWithStudentCount(reader);
        applicationMenu.findGroupsWithStudentCount(reader);
        verify(groupJdbcService, times(1)).findGroupsWithStudentCount(reader);
    }

    @Test
    void findStudentsRelatedToTheCourseServicesWasUsed() {
        doNothing().when(studentJdbcService).findStudentsRelatedToTheCourse(reader);
        applicationMenu.findStudentsRelatedToCourse(reader);
        verify(studentJdbcService, times(1)).findStudentsRelatedToTheCourse(reader);
    }

    @Test
    void removeStudentFromTheCourseServicesWasUsed() {
        doNothing().when(courseJdbcService).removeStudentFromTheCourse(reader);
        applicationMenu.removeStudentFromCourse(reader);
        verify(courseJdbcService, times(1)).removeStudentFromTheCourse(reader);
    }
}
