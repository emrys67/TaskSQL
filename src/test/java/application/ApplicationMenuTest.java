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
    private AddStudentToTheCourseService addStudentToTheCourseService;
    @Mock
    private AddStudentService addStudentService;
    @Mock
    private DeleteStudentService deleteStudentService;
    @Mock
    private FindStudentsRelatedToTheCourseService findStudentsRelatedToTheCourseService;
    @Mock
    private FindGroupsWithStudentCountService findGroupsWithStudentCountService;
    @Mock
    private RemoveStudentFromCourseService removeStudentFromCourseService;

    @BeforeEach
    void setUp() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        applicationMenu = new ApplicationMenu(addStudentService, addStudentToTheCourseService, deleteStudentService,
                findGroupsWithStudentCountService, findStudentsRelatedToTheCourseService, removeStudentFromCourseService);
    }

    @Test
    void addStudentServicesWasUsed() {
        doNothing().when(addStudentService).serve(reader);
        applicationMenu.addStudent(reader);
        verify(addStudentService, times(1)).serve(reader);
    }

    @Test
    void addStudentToCourseServicesWasUsed() {
        doNothing().when(addStudentToTheCourseService).serve(reader);
        applicationMenu.addStudentToCourse(reader);
        verify(addStudentToTheCourseService, times(1)).serve(reader);
    }

    @Test
    void deleteStudentServicesWasUsed() {
        doNothing().when(deleteStudentService).serve(reader);
        applicationMenu.deleteStudent(reader);
        verify(deleteStudentService, times(1)).serve(reader);
    }

    @Test
    void findGroupsWithStudentServicesWasUsed() {
        doNothing().when(findGroupsWithStudentCountService).serve(reader);
        applicationMenu.findGroupsWithStudentCount(reader);
        verify(findGroupsWithStudentCountService, times(1)).serve(reader);
    }

    @Test
    void findStudentsRelatedToTheCourseServicesWasUsed() {
        doNothing().when(findStudentsRelatedToTheCourseService).serve(reader);
        applicationMenu.findStudentsRelatedToCourse(reader);
        verify(findStudentsRelatedToTheCourseService, times(1)).serve(reader);
    }

    @Test
    void removeStudentFromTheCourseServicesWasUsed() {
        doNothing().when(removeStudentFromCourseService).serve(reader);
        applicationMenu.removeStudentFromCourse(reader);
        verify(removeStudentFromCourseService, times(1)).serve(reader);
    }
}
