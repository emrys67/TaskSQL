package application;

import com.foxminded.jdbc.application.ApplicationMenu;
import com.foxminded.jdbc.entity.Group;
import com.foxminded.jdbc.entity.Student;
import com.foxminded.jdbc.services.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ApplicationMenuTest {
    private ApplicationMenu applicationMenu;
    private BufferedReader reader;
    @Mock
    private StudentService studentService;
    @Mock
    private GroupService groupService;
    @Mock
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        applicationMenu = new ApplicationMenu(studentService, courseService, groupService);
    }

    @Test
    void addStudentServicesWasUsed() {
        doNothing().when(studentService).addStudent(reader);
        applicationMenu.addStudent(reader);
        verify(studentService, times(1)).addStudent(reader);
    }

    @Test
    void addStudentToCourseServicesWasUsed() {
        doNothing().when(courseService).addStudentToTheCourse(reader);
        applicationMenu.addStudentToCourse(reader);
        verify(courseService, times(1)).addStudentToTheCourse(reader);
    }

    @Test
    void deleteStudentServicesWasUsed() {
        doNothing().when(studentService).deleteStudent(reader);
        applicationMenu.deleteStudent(reader);
        verify(studentService, times(1)).deleteStudent(reader);
    }

    @Test
    void findGroupsWithStudentServicesWasUsed() {
        List<Group> list = new ArrayList<>();
        when(groupService.findGroupsWithStudentCount(reader)).thenReturn(list);
        applicationMenu.findGroupsWithStudentCount(reader);
        verify(groupService, times(1)).findGroupsWithStudentCount(reader);
    }

    @Test
    void findStudentsRelatedToTheCourseServicesWasUsed() {
        List<Student> list = new ArrayList<>();
        when(studentService.findStudentsRelatedToTheCourse(reader)).thenReturn(list);
        applicationMenu.findStudentsRelatedToCourse(reader);
        verify(studentService, times(1)).findStudentsRelatedToTheCourse(reader);
    }
}
